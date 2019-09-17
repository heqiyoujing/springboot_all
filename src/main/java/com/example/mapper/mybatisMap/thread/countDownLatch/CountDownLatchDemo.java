package com.example.mapper.mybatisMap.thread.countDownLatch;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *https://www.cnblogs.com/nullzx/p/5272807.html
 * CountDownLatch和CyclicBarrier的比较
 1.CountDownLatch是线程组之间的等待，即一个(或多个)线程等待N个线程完成某件事情之后再执行；
 而CyclicBarrier则是线程组内的等待，即每个线程相互等待，即N个线程都被拦截之后，然后依次执行。
 2.CountDownLatch是减计数方式，而CyclicBarrier是加计数方式。
 3.CountDownLatch计数为0无法重置，而CyclicBarrier计数达到初始值，则可以重置。
 4.CountDownLatch不可以复用，而CyclicBarrier可以复用。
 */
public class CountDownLatchDemo {
    private CountDownLatch cdl = new CountDownLatch(2);
    private Random rnd = new Random();

    class FirstTask implements Runnable{
        private String id;

        public FirstTask(String id){
            this.id = id;
        }

        @Override
        public void run(){
            System.out.println("Thread "+ id + " is start");
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread "+ id + " is over");
            cdl.countDown(); //其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)
        }
    }

    class SecondTask implements Runnable{
        private String id;

        public SecondTask(String id){
            this.id = id;
        }

        @Override
        public void run(){
            try {
                cdl.await(); //当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----------Thread "+ id + " is start");
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----------Thread "+ id + " is over");
        }
    }

    public static void main(String[] args){
        ExecutorService es = Executors.newCachedThreadPool();
        CountDownLatchDemo cdld = new CountDownLatchDemo();
        es.submit(cdld.new SecondTask("c"));
        es.submit(cdld.new SecondTask("d"));
        es.submit(cdld.new FirstTask("a"));
        es.submit(cdld.new FirstTask("b"));
        es.shutdown();
    }
}
