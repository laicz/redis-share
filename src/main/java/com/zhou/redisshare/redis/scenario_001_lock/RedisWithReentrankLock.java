package com.zhou.redisshare.redis.scenario_001_lock;

import com.google.common.collect.Maps;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis 使用场景 -- 实现分布式锁
 * 支持冲入锁
 * Created by zhoumb on 2019/1/2
 */
public class RedisWithReentrankLock {
    private RedisTemplate redisTemplate;

    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<>();

    public RedisWithReentrankLock(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private Boolean _lock(String key) {
        return redisTemplate.opsForValue().setIfAbsent(key, "", 100L, TimeUnit.SECONDS);
    }

    private Boolean _unlock(String key) {
        return redisTemplate.delete(key);
    }

    private Map<String, Integer> currentLockers() {
        Map<String, Integer> refs = lockers.get();
        if (refs != null) {
            return refs;
        }
        lockers.set(Maps.newHashMap());
        return lockers.get();
    }

    public boolean lock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt != null) {
            refs.put(key, refCnt + 1);
            return true;
        }
        boolean ok = this._lock(key);
        if (!ok) {
            return false;
        }
        refs.put(key, 1);
        return true;
    }

    public boolean unlock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt == null) {
            return false;
        }
        refCnt -= 1;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }


}
