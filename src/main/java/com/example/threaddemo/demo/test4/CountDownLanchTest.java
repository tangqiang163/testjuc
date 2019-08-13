package com.example.threaddemo.demo.test4;

import java.util.concurrent.CountDownLatch;

/**
 *  多线程倒计时计数器
 *
 *  应用场景
 *
 *  内部服务并行调用
 *  火箭发生升空前  各项准备工作必须完成
 *
 */
public class CountDownLanchTest {


    public static void main(String[] args){

        final CountDownLatch countDownLatch = new CountDownLatch(5);

        LanchDemo lanchDemo = new LanchDemo(countDownLatch);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5 ; i++) {

            // 开启5个线程去执行目标类的run方法
            new Thread(lanchDemo).start();

        }





        try {
            // 当计数减到0才会去执行
            countDownLatch.await();

        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("执行间隔时间:"+(end-start));
    }

}

class LanchDemo implements Runnable{


    private CountDownLatch latch;


    public LanchDemo(CountDownLatch countDownLatch){
        this.latch = countDownLatch;
    }

        @Override
    public void run() {



        synchronized(this) {
            System.out.println("当前线程的名称："+Thread.currentThread().getName());
            System.out.println("当前线程类："+Thread.currentThread().getClass());
            try {
                for (int i = 0; i < 50000; i++) {

                    if (i%2 == 0){
                        System.out.println(i);
                    }

                }
            }finally {

                this.latch.countDown();
            }
        }
    }
}

