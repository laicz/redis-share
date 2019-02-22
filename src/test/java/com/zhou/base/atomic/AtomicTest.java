package com.zhou.base.atomic;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import java.util.concurrent.atomic.*;

/**
 * Atomic类
 * Created by zhoumb on 2019/2/22
 */
public class AtomicTest {

    /**
     * 内部利用Cas来进行原子性控制
     */
    @Test
    public void atomicIntegerTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        int i = atomicInteger.addAndGet(1);
        System.out.println(i);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        boolean result = atomicBoolean.compareAndSet(Boolean.TRUE, Boolean.FALSE);
        System.out.println("更新结果" + result);
        AtomicLong atomicLong = new AtomicLong();
        long l = atomicLong.incrementAndGet();
        System.out.println(l);
    }

    /**
     * 能够更新数组特定指定下标数据
     * 具有原子特性的数组类型
     * AtomicReferenceArray
     * AtomicLongArray
     * AtomicReferenceArray //能够指定泛型的数组数据
     */
    @Test
    public void arrayTest() {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        int i = atomicIntegerArray.incrementAndGet(5);
        System.out.println(i);

        AtomicReferenceArray<Person> personAtomicReferenceArray = new AtomicReferenceArray<>(10);
        boolean b = personAtomicReferenceArray.compareAndSet(1, new Person("", 12), new Person("", 123));

    }

    /**
     * 使用AtomicReferenceField来进行对象的属性的更改
     * AtomicReferenceAdapter.newUpdater(Clazz,Clazz,fieldName)
     * 被设置修改的对象的值一定是引用类型，并且别volatile关键字修饰
     */
    @Test
    public void atomicReferenceField() {
        AtomicReferenceFieldUpdater<Person, Integer> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Person.class, Integer.class, "age");

        Person person = new Person("chou", 12);
        boolean set = fieldUpdater.compareAndSet(person, 12, 17);
        System.out.println("set=" + set + "," + person);
    }

    class Person {
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String name;
        volatile Integer age;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    /**
     * synchronized的用法
     * ①：修饰对象
     * ②：修饰静态对象
     * ④：修饰方法
     * ⑤：修饰静态方法
     */
    @Test
    public void testSynchronized() {
        synchronized (this) {

        }
    }

}
