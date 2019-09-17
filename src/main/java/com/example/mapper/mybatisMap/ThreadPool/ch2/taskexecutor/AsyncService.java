package com.example.mapper.mybatisMap.ThreadPool.ch2.taskexecutor;
//组件声明类
import org.springframework.stereotype.Service;
//异步声明,如果在方法表示是异步方法，如果在类表示异步类。
//这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor（线程池）
import org.springframework.scheduling.annotation.Async;

//声明为组件
@Service
public class AsyncService {
    /**
     * https://www.cnblogs.com/dyppp/category/1058372.html  Spring Boot实战笔记
     * @param i
     */


    //异步声明,如果在方法处表示是异步方法，如果在类处表示异步类（所有的方法都是异步方法）。
    //这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor（线程池）
    @Async
    public void executorAsyncTask(Integer i)
    {
        System.out.println("执行异步：" + i);
    }


    //异步声明,如果在方法处表示是异步方法，如果在类处表示异步类（所有的方法都是异步方法）。
    //这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor（线程池）
    @Async
    public void executorAsyncTaskPlus(Integer i)
    {
        System.out.println("执行异步任务+1: " + (i+1));
    }

}
