package com.example.mapper.mybatisMap.thread;

/**
 * 通过实现Runnable接口来创建线程
 * https://blog.csdn.net/qq_20916555/article/details/51180005
 * https://blog.csdn.net/xdd19910505/article/details/50958099
 */
public class ThreadCreateImplements implements Runnable {
    private int count;  //用同一个NewThread类开辟的不同线程共享同一个类的成员变量
    @Override
    public void run() {
        for(count = 1; count < 5; count++){
            System.out.println(Thread.currentThread().getName() + " :" + count);
        } //利用Thread.currentThread()获得当前进程
    }
    /*(输出结果不唯一),通过同一个实现Runnable接口的类创建多个子线程共享该类的
    成员变量count = 5,所以两个子线程Thread-0 和Thread-1一共输出5次，主线程输出10次。*/
    public static void main(String[] agrs){
        ThreadCreateImplements nt = new ThreadCreateImplements();
        for (int j = 1; j <= 10; j++) {
            System.out.println(Thread.currentThread().getName()+" :"+j);
            if(j == 5){
                new Thread(nt).start();
                new Thread(nt).start();  //俩个子线程和主线程并发执行
            }
        }
    }
}
