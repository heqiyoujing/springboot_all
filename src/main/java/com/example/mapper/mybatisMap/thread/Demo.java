package com.example.mapper.mybatisMap.thread;

public class Demo implements Runnable{
    public void run()
    {
        for(int x=1; x<100; x++)
        {
            System.out.println(Thread.currentThread().getName() + "..." + x);
        }
    }
}
