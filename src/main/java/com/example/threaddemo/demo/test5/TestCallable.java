package com.example.threaddemo.demo.test5;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  创建执行线程的方式三
 *      实现的callable接口  相较于runable接口  callable可以又返回值 抛出异常
 *
 *      执行callable接口需要Future实现类的支持用于接受运算结果  FutureTask是Future的实现类
 *
 *  闭锁：latch
 *      　闭锁可以延迟线程的进度直到线程线程到达终止状态。一个闭锁工作起来就像是一道大门：直到闭锁达到终点状态之前，
 *        门一直是关闭的，没有线程能够通过，在终点状态到来的时候，所有线程都可以通过。
 */
public class TestCallable {

    public static void main(String[] args){

        CallableDemo demo = new CallableDemo();


        FutureTask task = new FutureTask<>(demo);


        new Thread(task).start();

        try {

            //FutureTask 可用于 闭锁
            Integer sum = (Integer) task.get();


            System.out.println("sum="+sum);
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}

class CallableDemo implements Callable{

    @Override
    public Integer call() throws Exception {

        int sum = 0;

        for (int i = 0; i <100 ; i++) {
            sum+=i;
        }

        return sum;
    }
}

