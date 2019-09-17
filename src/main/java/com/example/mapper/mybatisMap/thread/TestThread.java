package com.example.mapper.mybatisMap.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TestThread {
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    /*应用场景1：
    https://blog.csdn.net/zhutulang/article/details/48504487
    假设一条流水线上有三个工作者：worker0，worker1，worker2。有一个任务的完成需要他们三者协作完成，
    worker2可以开始这个任务的前提是worker0和worker1完成了他们的工作，而worker0和worker1是可以并行他们各自的工作的。*/
    public static void main(String[] args) throws InterruptedException {
        // TODO 自动生成的方法存根

        // 创建了一个计数器为2的 CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(2);
        // Worker持有这个CountDownLatch 实例
        Worker worker0 = new Worker("worker0", (long) (Math.random()*2000+3000), countDownLatch);
        Worker worker1 = new Worker("worker1", (long) (Math.random()*2000+3000), countDownLatch);
        Worker worker2 = new Worker("worker2", (long) (Math.random()*2000+3000), countDownLatch);

        executor.execute(worker0);
        executor.execute(worker1);

        /*必须在work1和work0完成以后，才能进行work2工作。
        countDownLatch.await() 方法会一直阻塞直到计数器为0，主线程才会继续往下执行*/
        countDownLatch.await();
        System.out.println("准备工作就绪");
        // worker2.start()和executor.execute(worker2)是一样的意思
        worker2.start();
    }
}
