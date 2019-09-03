package com.example.threaddemo.demo.test9;

/**
 * @Author: tangqiang
 * @Date: 2019/8/28 10:27
 * @Version 1.0
 */
public class Test9Demo implements Person{


    private Person p;

    public Test9Demo(Person person){
        this.p = person;

    }

    public static void main(String[] args) {

        Student student = new Student();

        //创建代理类对象
        Test9Demo proxy = new Test9Demo(student);

        //调用代理类对象的方法
        proxy.sayHello("welcome to java", 20);
        System.out.println("******");

        //调用代理类对象的方法
        proxy.sayGoodBye(true, 100);


    }

    @Override
    public void sayHello(String content, int age) {
        this.p.sayHello(content,age);
    }

    @Override
    public void sayGoodBye(boolean seeAgin, double time) {
        this.p.sayGoodBye(seeAgin,time);
    }
}
