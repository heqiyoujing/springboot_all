package com.example.mapper.mybatisMap.library;

import java.util.concurrent.ConcurrentLinkedQueue;

public class LimitQueue<E>{

    private int limit; // 队列长度

    private ConcurrentLinkedQueue<E> queue = new ConcurrentLinkedQueue<E>();

    public LimitQueue(int limit){
        this.limit = limit;
    }

    /**
     * 入列：当队列大小已满时，把队头的元素poll掉
     */
    public void offer(E e){
        if(queue.size() >= limit){
            queue.poll();
        }
        queue.offer(e);
    }
    public boolean contains(E e){
        return queue.contains(e);
    }

    public int getLimit() {
        return limit;
    }

    public int size() {
        return queue.size();
    }

}
