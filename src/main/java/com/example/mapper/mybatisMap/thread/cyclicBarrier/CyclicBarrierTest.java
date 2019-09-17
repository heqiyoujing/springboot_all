package com.example.mapper.mybatisMap.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * https://www.cnblogs.com/200911/p/6060195.html
 * CyclicBarrier 字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是让一组线程到达一个屏障（同步点）时
 * 被阻塞，直到最后一个线程到达屏障时候，屏障才会开门。所有被屏障拦截的线程才会运行。
 *CyclicBarrier [ˈsaɪklɪk] [ˈbæriə(r)]
 */
public class CyclicBarrierTest {
    private static int SIZE = 5;

    private static CyclicBarrier cb;

    public static void main(String[] args) {
        cb = new CyclicBarrier(SIZE);
        for (int i = 0; i < SIZE; i++) {
            new MyTask().start();
        }

    }

    static class MyTask extends Thread {
        @Override
        public void run() {
            try {

                System.out.println("线程" + Thread.currentThread().getName() + "正在执行同一个任务");
                // 以睡眠来模拟几个线程执行一个任务的时间
                Thread.sleep(1000);
                System.out.println("线程" + Thread.currentThread().getName() + "执行任务完成，等待其他线程执行完毕");
                // 用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
                cb.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕");

        }

    }

}
