package com.example.threaddemo.demo.test7;

/**
 * 线程等待唤醒 - 生产消费案例
 * 公共资源：货物
 * 生产货物：类似普通多线程的环境下的set方法
 * 消费货物：类似普通多线程的环境下的get方法
 *
 * 思考线程唤醒的操作-唤醒等待机制几个状态切换
 *
 */
public class TestProductAndConsumer {

    public static void main(String[] args){

        Clerk clerk = new Clerk();

        new Thread(new Product(clerk),"生产线程").start();
        new Thread(new Consumer(clerk),"消费线程").start();

    }

}

class Clerk {


    private int products = 0;

    // 卖货
    public synchronized void sale(){


        while (products==0){

            System.out.println("货卖完了,缺货！");

            try {

                this.wait();

            }catch (Exception ex){

            }

        }

        System.out.println(Thread.currentThread().getName()+"--卖货--"+--products);
        this.notifyAll();
    }

    // 进货
    public synchronized void get(){

        while (products>=20){
            System.out.println("进货已满！");

            try {

                this.wait();

            }catch (Exception ex){

            }
        }

        System.out.println(Thread.currentThread().getName()+"--进货--"+(++products));
        this.notifyAll();

    }

}

class Product implements Runnable{

    private Clerk clerk;

    public Product(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {

        for (int i = 0; i < 2000; i++) {


            try {
                Thread.sleep(100);
            }catch (Exception ex){

            }

            this.clerk.get();


        }

    }
}

class Consumer implements Runnable{

    private Clerk clerk;

    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i <2000 ; i++) {
            this.clerk.sale();
        }
    }
}
