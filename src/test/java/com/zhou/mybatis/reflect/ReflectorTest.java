package com.zhou.mybatis.reflect;

import com.zhou.redisshare.model.User;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoumb on 2019/4/9
 */
public class ReflectorTest {

    @Test
    public void reflector() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        User user = new User();
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        Reflector reflector = reflectorFactory.findForClass(User.class);
        Invoker usernameInvoker = reflector.getSetInvoker("username");
        usernameInvoker.invoke(user, new String[]{"reflector set name"});
        System.out.println(reflector.getGetInvoker("username").invoke(user, null));
        Constructor<?> defaultConstructor = reflector.getDefaultConstructor();
        Object object = defaultConstructor.newInstance();
        System.out.println(object);
    }

    @Test
    public void testComputeIfAbsent() {
        Map<String, List<String>> stringListMap = new HashMap<>();
        List<String> list = stringListMap.computeIfAbsent("key1", k -> Lists.newArrayList());
        list.add("hah");
        System.out.println(stringListMap);
    }

    @Test
    public void isAssignableFrom() {
        A a = new A();
        B b = new B();
        System.out.println(a.getClass().isAssignableFrom(b.getClass()));
        System.out.println(b.getClass().isAssignableFrom(a.getClass()));
    }

    class A {

    }

    class B extends A {

    }

    @Test
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -3);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(calendar.getTime()));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK) == 1);
        System.out.println(calendar.getWeekYear());
    }
}
