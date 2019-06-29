package com.example.threaddemo.demo.test1;

/**
 * volatile关键修饰
 *  1.volatile修饰的变量不允许线程缓存和重排序，即直接修改堆内存中的数据，保证了线程之间操作同一资源的可见性
 *
 *  注意点:volatile能够提供线程间的可见性 但不能支持原子性操作
 */
public class TestVolatile {

    public static void main(String[] args){


        ThreadDemo threadDemo = new ThreadDemo();

        // 开始开启线程
        new Thread(threadDemo).start();


        while (true){

            if (threadDemo.isFlag()){
                System.out.println("主线程执行了");
                break;
            }

        }

    }

}

class ThreadDemo implements Runnable {

    //private boolean flag = false;
    // 增加volatile修饰  保持其内存可见性
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        this.flag = true;

        System.out.println("flag="+flag);
    }
}
