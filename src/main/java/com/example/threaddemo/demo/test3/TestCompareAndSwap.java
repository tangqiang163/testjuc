package com.example.threaddemo.demo.test3;

/**
 * @Author: tangqiang
 * @Date: 2019/7/2 14:29
 * @Version 1.0
 *
 * 模拟CAS算法
 *
 *  V 内存值
 *
 *  A 预估值
 *
 *  B 更新值
 *
 *  当V==A 时  V=B
 */
public class TestCompareAndSwap {

    public static void main(String[]args){

        final CompareAndSwap compareAndSwap = new CompareAndSwap();

        for (int i = 0; i <10 ; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 重新获取内存值
                    int A = compareAndSwap.getV();

                    boolean flag = compareAndSwap.compareAndS(A,(int)Math.random()*101);

                    System.out.println("flag = "+flag);

                }
            }).start();

        }




    }

}

/**
 * 模拟比较类
 *
 * 避免干扰因素的影响 - 操作资源的时候 都加上sysnchronized
 *  保证操作的理论上的原子性
 *
 */
class CompareAndSwap{
    private int V;


    // 同步环境下获取内存值
    public synchronized int getV(){
        return this.V;
    }


    // 同步环境下compareAndSwap
    public synchronized boolean compareAndS(int A,int B){

        try {
            Thread.sleep(200);
        }catch (Exception ex){

        }

        int oldValue = this.getV();

        if (oldValue == A){
            this.V = B;
            return true;
        }else {

            return false;
        }


    }






}
