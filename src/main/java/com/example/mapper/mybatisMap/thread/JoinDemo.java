package com.example.mapper.mybatisMap.thread;

public class JoinDemo {

    /*应用场景1：
    https://blog.csdn.net/zhutulang/article/details/48504487
    假设一条流水线上有三个工作者：worker0，worker1，worker2。有一个任务的完成需要他们三者协作完成，
    worker2可以开始这个任务的前提是worker0和worker1完成了他们的工作，而worker0和worker1是可以并行他们各自的工作的。*/
    public static void main(String[] args) throws Exception
    {
        Demo d = new Demo();
        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);

        t1.start();

        /* 主线程向下转时，碰到了t1.join()，t1要申请加入到运行中来，就是要CPU执行权。
         这时候CPU执行权在主线程手里，主线程就把CPU执行权给放开，陷入冻结状态。
         活着的只有t1了，只有当t1拿着执行权把这些数据都打印完了，主线程才恢复到运行中来*/
        t1.join();

        t2.start();

        for(int x=1; x<100; x++)
        {
            System.out.println(Thread.currentThread().getName() + "..." + x);
        }
    }
}
