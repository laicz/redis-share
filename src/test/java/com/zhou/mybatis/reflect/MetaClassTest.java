package com.zhou.mybatis.reflect;

import com.zhou.redisshare.model.User;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.junit.Test;

/**
 * MetaClass测试类
 * Created by zhoumb on 2019/4/10
 */
public class MetaClassTest {

    @Test
    public void propertyTest() {
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        MetaClass metaClass = MetaClass.forClass(User.class, reflectorFactory);
        MetaClass usernameMetaClass = metaClass.metaClassForProperty("username");
        System.out.println(metaClass.findProperty("userExtension.companyId"));
        String property = metaClass.findProperty("userExtension.companyId");
        System.out.println(metaClass.findProperty("userExtensionList[0].companyId"));
        Class<?> getterType = metaClass.getGetterType(property);
        MetaClass metaClass1 = MetaClass.forClass(getterType, reflectorFactory);
    }
}
