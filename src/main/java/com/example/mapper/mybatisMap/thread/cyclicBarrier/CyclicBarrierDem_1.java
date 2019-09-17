package com.example.mapper.mybatisMap.thread.cyclicBarrier;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.*;

/**循环栅栏
 * https://blog.csdn.net/carson0408/article/details/79471490
 *在CyclicBarrier的内部定义了一个Lock对象，每当一个线程调用await方法时，将拦截的线程数减1，然后判断剩余拦截数是否
 * 为初始值parties，如果不是，进入Lock对象的条件队列等待。如果是，执行barrierAction对象的Runnable方法，然后将锁的
 * 条件队列中的所有线程放入锁等待队列中，这些线程会依次的获取锁、释放锁。
 * 如果一个寝室四个人约好了去球场打球，由于四个人准备工作不同，所以约好在楼下集合，并且四个人集合好之后一起出发去球场。
 */
public class CyclicBarrierDem_1 {
    private static final ThreadPoolExecutor threadPool=new ThreadPoolExecutor(4,10,60,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
    //当拦截线程数达到4时，便优先执行barrierAction，然后再执行被拦截的线程。
    private static final CyclicBarrier cb=new CyclicBarrier(4,new Runnable() {
        public void run()
        {
            System.out.println("寝室四兄弟一起出发去球场");
        }
    });
    private static class GoThread extends Thread{
        private final String name;
        public GoThread(String name)
        {
            this.name=name;
        }
        public void run()
        {
            System.out.println(name+"开始从宿舍出发");
            try {
                Thread.sleep(1000);
                cb.await();//拦截线程
                System.out.println(name+"从楼底下出发");
                Thread.sleep(1000);
                System.out.println(name+"到达操场");

            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            catch(BrokenBarrierException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] str= {"李明","王强","刘凯","赵杰"};
        for(int i=0;i<4;i++)
        {
            threadPool.execute(new GoThread(str[i]));
        }
        try
        {
            Thread.sleep(4000);
            System.out.println("四个人一起到达球场，现在开始打球");
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }


    }

}
