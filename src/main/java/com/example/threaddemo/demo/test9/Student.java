package com.example.threaddemo.demo.test9;

/**
 * @Author: tangqiang
 * @Date: 2019/8/28 10:31
 * @Version 1.0
 */
public class Student implements Person {
    @Override
    public void sayHello(String content, int age) {
        // TODO Auto-generated method stub
        System.out.println("student say hello" + content + " "+ age);
    }

    @Override
    public void sayGoodBye(boolean seeAgin, double time) {
        // TODO Auto-generated method stub
        System.out.println("student sayGoodBye " + time + " "+ seeAgin);
    }

}
