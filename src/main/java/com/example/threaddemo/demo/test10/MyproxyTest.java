package com.example.threaddemo.demo.test10;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * JDK动态代理 - review
 *
 * 1）通过实现InvocationHandler接口来自定义自己的InvocationHandler；
 *
 * 2）通过Proxy.getProxyClass获得动态代理类；
 *
 * 3）通过反射机制获得代理类的构造方法，方法签名为getConstructor(InvocationHandler.class)；
 *
 * 4）通过构造函数获得代理对象并将自定义的InvocationHandler实例对象传为参数传入；
 *
 * 5）通过代理对象调用目标方法；
 *
 */
public class MyproxyTest {

    public static void main(String[] args) throws Exception{

        /**
         * 方式一
         */
        // 1、生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 2、获取动态代理类
        Class proxyClazz = Proxy.getProxyClass(IHello.class.getClassLoader(),IHello.class);
        // 3、获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        // 4、通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        IHello iHello1 = (IHello) constructor.newInstance(new MyInvocationHandler(new HelloImpl()));
        // 5、通过代理对象调用目标方法
        iHello1.sayHello();


        Integer[] invalid = new Integer[]{0,2,1,4,8};
        if (Arrays.asList(invalid).contains(0)){
            System.out.println("11");
        }


        /**
         * 方式2
         */
        /**
         * Proxy类中还有个将2~4步骤封装好的简便方法来创建动态代理对象，
         *其方法签名为：newProxyInstance(ClassLoader loader,Class<?>[] instance, InvocationHandler h)
         */
        IHello  iHello2 = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), // 加载接口的类加载器
                new Class[]{IHello.class}, // 一组接口
                new MyInvocationHandler(new HelloImpl())); // 自定义的InvocationHandler
        iHello2.sayHello();


    }
}
