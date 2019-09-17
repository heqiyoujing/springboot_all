package com.example.mapper.mybatisMap.thread;

import java.util.concurrent.CountDownLatch;

public class Worker extends Thread {

    //工作者名
    private String name;
    //工作时间
    private long time;

    private CountDownLatch countDownLatch;

    public Worker(String name, long time, CountDownLatch countDownLatch) {
        this.name = name;
        this.time = time;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        // TODO 自动生成的方法存根
        try {
            System.out.println(name+"开始工作");
            Thread.sleep(time);
            System.out.println(name+"工作完成，耗费时间="+time);
            // 完成自己的工作后，调用countDownLatch.countDown() 方法将计数器减1
            countDownLatch.countDown(); //最好写在finally, 不然的话出现异常会导致无法减一，然后出现死锁的.
            System.out.println("countDownLatch.getCount()="+countDownLatch.getCount());
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}

