package com.example.threaddemo.demo.test10;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: tangqiang
 * @Date: 2019/9/3 14:58
 * @Version 1.0
 */
public class CGlibProxy implements MethodInterceptor {

    // 需要代理的目标对象
    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("Cglib动态代理,监听开始！");

        // 方法执行,参数:target目标对象 arr参数数组
        Object invoke = method.invoke(target,objects);

        System.out.println("Cglib动态代理,监听结束！");

        return invoke;
    }

    // 定义获取代理对象方法
    public Object getCglibProxy(Object objectTarget){

        // 为目标对象target赋值
        this.target = objectTarget;

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(objectTarget.getClass());

        enhancer.setCallback(this);

        Object result = enhancer.create();

        return result;
    }

    public static void main(String[] args) {

        CGlibProxy cglib = new CGlibProxy();

        IHello cglibProxy = (IHello) cglib.getCglibProxy(new HelloImpl());

        cglibProxy.sayHello();


    }


}
