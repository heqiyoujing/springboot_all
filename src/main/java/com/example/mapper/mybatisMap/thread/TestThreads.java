package com.example.mapper.mybatisMap.thread;

import java.util.concurrent.CountDownLatch;

public class TestThreads {

   /* 应用场景2：
   https://blog.csdn.net/zhutulang/article/details/48504487
    假设worker的工作可以分为两个阶段，work2 只需要等待work0和work1完成他们各自工作的第一个阶段之后就可以
    开始自己的工作了，而不是场景1中的必须等待work0和work1把他们的工作全部完成之后才能开始。*/
    public static void main(String[] args) throws InterruptedException {
        // TODO 自动生成的方法存根

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Workers worker0 = new Workers("worker0", (long) (Math.random()*2000+3000), countDownLatch);
        Workers worker1 = new Workers("worker1", (long) (Math.random()*2000+3000), countDownLatch);
        Workers worker2 = new Workers("worker2", (long) (Math.random()*2000+3000), countDownLatch);

        worker0.start();
        System.out.println("--------------------");
        worker1.start();
        countDownLatch.await();

        System.out.println("准备工作就绪");
        worker2.start();

    }
}
