package com.example.threaddemo.demo.test12;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 *  读写锁
 *
 *  写写 or 读写 需要互斥
 *
 */
public class TestReadWriteLock {

    public static void main(String[] args){
        
        DemoReadWriteLock demo = new DemoReadWriteLock();



        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.getNum();
                }
            },"read").start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.setNum(88);
            }
        },"write").start();

    }

}

class DemoReadWriteLock{

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private int num = 0;


    public void setNum(int num){

        lock.writeLock().lock();

        try {
            this.num = num;
            System.out.println("Name:"+Thread.currentThread().getName()+"   num:"+this.num);

        }finally {
            lock.writeLock().unlock();
        }



    }

    public void getNum(){
        lock.readLock().lock();

        try {

            System.out.println("Name:"+Thread.currentThread().getName()+"   num:"+this.num);

        }finally {
            lock.readLock().unlock();
        }

    }

}
