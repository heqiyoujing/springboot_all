package com.example.mapper.mybatisMap.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolK {
    private static int POOL_NUM = 3;

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i<POOL_NUM; i++)
        {
            RunnableThreads thread = new RunnableThreads();
            executorService.execute(thread);
        }
    }
}

class RunnableThreads implements Runnable
{
    private int THREAD_NUM = 4;
    public void run()
    {
        for(int i = 0; i<THREAD_NUM; i++)
        {
            System.out.println("线程" + Thread.currentThread() + " " + i);
        }
    }
}
