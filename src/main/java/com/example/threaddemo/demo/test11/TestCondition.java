package com.example.threaddemo.demo.test11;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程  分别打印ABC十次  有序
 *
 * condition通信
 *
 */
public class TestCondition {

    public static void main(String[] args){

        MethodO o = new MethodO();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    o.methodA();
                }

            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    o.methodB();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    o.methodC();
                }
            }
        },"C").start();

    }

}

class MethodO{

    private int num = 1;

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();

    private Condition condition2 = lock.newCondition();

    private Condition condition3 = lock.newCondition();

    public void methodA(){

        lock.lock();
        try {

            // 判断当前线程是几号线程
            if (num != 1){
                condition1.await();
            }

            // 打印
            for (int i = 0; i < 1; i++) {
                System.out.println("name:"+Thread.currentThread().getName());
            }

            // 唤醒 - 唤醒的同时意味着释放锁？
            num = 2;
            condition2.signal();


        }catch (Exception ex){
           ex.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void methodB(){
        lock.lock();
        try {

            // 判断当前线程是几号线程
            if (num != 2){
                condition2.await();
            }

            // 打印
            for (int i = 0; i < 1; i++) {
                System.out.println("name:"+Thread.currentThread().getName());
            }

            // 唤醒
            num = 3;
            condition3.signal();


        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void methodC(){
        lock.lock();
        try {

            // 判断当前线程是几号线程
            if (num != 3){
                condition3.await();
            }

            // 打印
            for (int i = 0; i < 1; i++) {
                System.out.println("name:"+Thread.currentThread().getName());
            }

            // 唤醒
            num = 1;
            condition1.signal();


        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}