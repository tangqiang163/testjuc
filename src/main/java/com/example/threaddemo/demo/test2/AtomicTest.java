package com.example.threaddemo.demo.test2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子操作
 *  原子操作- 最小单位操作 - 不可分割
 *
 *  num++ 可拆分为
 *
 *  int temp = num
 *  num = num+1
 *  num = temp
 *
 *  CAS(Compare-And-Swap)算法保证数据的原子性
 *      CAS包含三个操作数
 *      内存值V
 *      预估值A
 *      更新值B
 *      当且仅当V==A时 V=B 否则将不做任何操作
 *
 */
public class AtomicTest {

    public static void main(String[] args){


         AtomicDemo demo = new AtomicDemo();
        for (int i = 0; i <10 ; i++) {
            // 多个线程操作同一资源
            new Thread(demo).start();

        }


    }

}

/**
 * AtomicInteger,AtomicInteger同属concurrent封装的包内容
 * 保证内存的可见性和原子性
 */
class AtomicDemo implements Runnable{

    // 资源
    //private volatile int num = 0;
    private AtomicInteger num = new AtomicInteger();

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        }catch (Exception ex){

        }


        System.out.println(num.getAndIncrement());
    }
}
