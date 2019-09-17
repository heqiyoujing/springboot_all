package com.example.mapper.mybatisMap.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * https://blog.csdn.net/xdd19910505/article/details/50958099
 * 通过实现Callable接口利用TaskFutrue来创建线程
 */
public class ThreadCreateCallable implements Callable<Integer> {
    public static void main(String[] args)
    {
        ThreadCreateCallable ctt = new ThreadCreateCallable();
        FutureTask<Integer> ft = new FutureTask<Integer>(ctt);
//        Thread thread = new Thread(ft,"有返回值的线程");
//        thread.start();
        for(int i = 0;i < 20;i++)
        {
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
            if(i==2)
            {
                new Thread(ft,"有返回值的线程").start();
            }
        }
        try
        {
            System.out.println("子线程的返回值："+ft.get());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }

    }

    public Integer call() throws Exception
    {
        int i = 0;
        for(;i<10;i++)
        {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
        return i;
    }
}
