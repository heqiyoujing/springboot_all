package com.example.mapper.mybatisMap.threadSafe;


import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class StatelessFactorizer {
    /**
     * https://www.2cto.com/kf/201804/739929.html    线程安全性
     */
    //-----------------------原子性--------------
    private long counts = 0;
    public long getCounts() {
        return counts ;
    }
    public void services(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        counts++;
    }
    //-----------------------原子性--------------

    //--------------------竞态条件避免-----------------
    //  AtomicLong是一种替代long类型整数的线程安全类
    private final AtomicLong count = new AtomicLong(0);
    public long getCount() {
        return count .get() ;
    }
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        count.incrementAndGet();
    }
    //--------------------竞态条件避免-----------------

    //--------------------加锁-----------------
    private long count1 = 0;

    public long getCount1() {
        return count1 ;
    }
    public synchronized void service1(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        count1++;
    }
    //--------------------加锁-----------------

    //--------------------count加锁-----------------
    private long count2 = 0;

    public long getCount2() {
        return count2 ;
    }
    public void service2(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        synchronized(this){
            count2++;
        }
    }
    //--------------------count加锁-----------------

    //--------------------可见性-----------------
    private static boolean ready;
    private static int number;
    public static class ReadThread extends Thread {
        public void run() {
            while(!ready )
                Thread. yield();
            System. out.println(number);
        }
    }
    public static void main(String [] args) {
        new ReadThread().start();
        number = 42;
        ready = true ;
    }
    //--------------------可见性-----------------

    //----------------------------------------
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();
    // GuardedBy(lock）线程只有相似持有一个特定的锁（lock）后，才能访问某个域方法；
    // GuardedBy("this") 包含在对象中的内部锁（方法或域是这个对象的一个成员）
    public void service(){
        count.incrementAndGet();
    }
    //----------------------------------------

    //-------------------ThreadLocal类---------------------
    // 不同的线程执行同一段代码时，访问同一个ThreadLocal变量，但是每个线程只能看到私有的ThreadLocal实例
    private ThreadLocal myThreadLocal = new ThreadLocal();
    public void setThreadLocal(){
        myThreadLocal.set("A thread local value");
    }
    String threadLocalValue = (String)myThreadLocal.get();

    //为了使get()方法返回值不用做强制类型转换，通常可以创建一个泛型化的ThreadLocal对象。
    private ThreadLocal myThread = new ThreadLocal<String>();
    public void setThreadLocals(){
        myThread.set("hello");
    }
    String sss = (String) myThread.get();

    //-------------------ThreadLocal类---------------------
}
