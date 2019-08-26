package com.example.threaddemo.demo.test8;

/**
 * 异或运算review
 */
public class MyTest {

    public static void main(String[] args) {


        System.out.println("一个数与另一个数异或两次是其本身: "+(8^1^1));
        System.out.println("一个数异或本身结果为0: "+(8^8));
        System.out.println("一个数异或0结果为其本身: "+(8^0));

        method_1();
        method_2();
        //method_3();
    }

    /**
     *  仅限于重复一个数字
     *  1.产生一个由1-10的数字组成的数组
     *  2.然后，再插入1-10中的一个数组进入数组
     *  3.将该数组打乱，找出该重复元素
     *
     *  应用一个数异或两次是其本身
     */
    public static void method_1(){

        int a[] = {1,6,9,7,8,2,3,8,4,5,10};

        for (int i = 1; i <a.length ; i++) {

            a[0] ^= a[i];

        }

        System.out.println(a[0]);

        /**
         * 相当于
         * 1-10的数 都异或一边  再都异或一边  重复的数字 就会出现3遍
         *
         * 整体来看就是  X ^ X ^ 重复数字
         */
        for (int i = 1; i < 11 ; i++) {
            a[0] ^= i;
        }

        System.out.println("value = "+a[0]);

    }


    /**
     * a,b互换
     *
     * 应用一个数异或两次是其本身
     */
    public static void method_2(){

        int a = 3;
        int b = 4;

        a = a^b;
        // b = a^b^b  等价于 b = a
        b = a^b;
        // a = a^b^a^b^b  等价于 a = b
        a = a^b;

        System.out.println("a="+a);
        System.out.println("b="+b);

    }


    public static void method_3(){

        int a[] = {1,1,7,2,2};

        for (int i = 1; i <a.length ; i++) {

            a[0] ^= a[i];

        }

        System.out.println("test:"+a[0]);



    }

}
