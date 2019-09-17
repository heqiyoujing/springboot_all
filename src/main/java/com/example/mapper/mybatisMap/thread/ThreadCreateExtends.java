package com.example.mapper.mybatisMap.thread;

/**
 * 通过继承Thread类创建线程
 * https://blog.csdn.net/qq_20916555/article/details/51180005
 */
public class ThreadCreateExtends  extends Thread{

    private int count ; //每个新进程类都有独立的成员变量count

    public void run() {  //重写run方法
        for (count = 1; count <= 5; count++) {
            System.out.println(getName() + " :" + count);
        }
    }

    /*(输出结果不唯一)每个子线程都输出5次，主线程输出10次，因子进程通过继承Thread类创建，拥有自己独立的成员变量count=5；*/
    public static void main(String[] agrs) {
        for (int j = 1; j <= 10; j++) {
            System.out.println(Thread.currentThread().getName()+" :"+j);
            if(j == 5){
                new ThreadCreateExtends().run();
                new ThreadCreateExtends().run();  //俩个子线程和主线程并发执行
            }
        }
    }
}
