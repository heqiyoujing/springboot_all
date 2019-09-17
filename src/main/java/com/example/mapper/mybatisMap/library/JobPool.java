package com.example.mapper.mybatisMap.library;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 业务工作线程池
 * @author shidb
 *
 */
public class JobPool {

    private static ExecutorService pool;
    /**
     * 初始化工作线程池
     * @param threadNum 线程数
     * */
    public void initPool (int threadNum) {
        pool = Executors.newFixedThreadPool( threadNum > 0 ? threadNum : 20 );
    }
    public ExecutorService getPool () {
        return pool;
    }

}
