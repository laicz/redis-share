package com.zhou.redisshare.redis.scenario_002_delay_queue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Redis实现延迟队列
 * Created by zhoumb on 2019/1/3
 */
public class RedisDelayingQueue<T> {
    static class TaskItem<T> {
        public String id;
        public T msg;
    }

    //fastjson 序列化对象中存在 generic 类型时，需要使用TypeReference
    private Type TaskType = new TypeReference<TaskItem<T>>() {
    }.getType();

    private RedisTemplate redisTemplate;
    private String key;

    public RedisDelayingQueue(RedisTemplate redisTemplate, String key) {
        this.redisTemplate = redisTemplate;
        this.key = key;
    }

    public void delay(T msg) {
        TaskItem<T> task = new TaskItem<>();
        task.id = UUID.randomUUID().toString();
        task.msg = msg;
        String taskString = JSON.toJSONString(task);
        redisTemplate.opsForZSet().add(key, taskString, System.currentTimeMillis() + 5000); //塞入延迟队列  5s后再次重试
    }

    public void loop() {
        while (!Thread.interrupted()) {
            //只取一条
            Set<String> values = redisTemplate.opsForZSet().rangeByScore(key, 0, System.currentTimeMillis(), 0, 1);
            if (CollectionUtils.isEmpty(values)) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            if (redisTemplate.opsForZSet().remove(key, s) > 0) {//获取到 taskItem
                TaskItem taskItem = JSON.parseObject(s, TaskType);
                this.handleTaskItem(taskItem);
            }
        }
    }

    private void handleTaskItem(TaskItem taskItem) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd Hh:mm:ss.SSS");
        System.out.println(simpleDateFormat.format(new Date()) + "     处理延时任务：" + taskItem.msg);
    }
}
