package com.example.mapper.mybatisMap.threadSafe;

public class Child {
    public   static   void   main(String[] args) {
        Child child = new   Child();
        child.doSomething();
    }

    /**
     *  这里的对象锁只有一个,就是child对象的锁,当执行child.doSomething时，该线程获得child对象的锁，在doSomething方法内
     *  执行doAnotherThing时再次请求child对象的锁，因为synchronized是重入锁，所以可以得到该锁
     */
    public   synchronized   void   doSomething() {
        System.out.println( "child.doSomething()" );
        doAnotherThing(); // 调用自己类中其他的synchronized方法
    }
    private  synchronized   void   doAnotherThing() {
        new Father().doSomething(); // 调用父类的synchronized方法
        System.out.println( "child.doAnotherThing()" );
    }
}
