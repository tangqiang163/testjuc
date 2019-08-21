package com.example.threaddemo.demo.test6;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全
 * 1.同步方法
 * 2.同步代码块
 * 3.jdk1.5之后 Lock
 */
public class TestLock {

    public static void main(String[] args){

        Callable callable = new CallableDemo();

        FutureTask task = new FutureTask<>(callable);
        FutureTask task2 = new FutureTask<>(callable);
        FutureTask task3 = new FutureTask<>(callable);
        FutureTask task4 = new FutureTask<>(callable);
        FutureTask task5 = new FutureTask<>(callable);
        new Thread(task,"线程1").start();
        new Thread(task2,"线程2").start();
        new Thread(task3,"线程3").start();
        new Thread(task4,"线程4").start();
        new Thread(task5,"线程5").start();

/*
        Ticket ticket = new Ticket();
        new Thread(ticket,"线程4").start();
        new Thread(ticket,"线程5").start();
        new Thread(ticket,"线程6").start();


*/


    }

}

class CallableDemo implements Callable{

    private int ticket = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public Object call() throws Exception {

        while (ticket>0){
          try {




                lock.lock();

                if (ticket<=0) break;

                // 操作资源
                ticket--;
                System.out.println(Thread.currentThread().getName()+"卖票一张，剩余"+ticket);



            }catch (Exception ex){

            }finally {
              lock.unlock();
            }

            Thread.sleep(100);
        }


        System.out.println(Thread.currentThread().getName());

        return 1;
    }

}

class Ticket implements Runnable{

    private AtomicInteger tick = new AtomicInteger(100);

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){

            //lock.lock(); //上锁

            try{
                if(tick.get() > 0){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }

                    System.out.println(Thread.currentThread().getName() + " 完成售票,余票为：" + tick.getAndAdd(-1));
                }
            }finally{
              //  lock.unlock(); //释放锁
            }
        }
    }

}
