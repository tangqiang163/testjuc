package com.example.threaddemo.demo.test10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: tangqiang
 * @Date: 2019/8/28 14:59
 * @Version 1.0
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object object){
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println("前置代码处理！");

        // 执行相应的目标方法
        Object obj = method.invoke(this.target,args);

        System.out.println("后置代码处理！");

        return obj;
    }
}
