package com.example.mapper.mybatisMap.threadSafe;


public class Father {
    public   synchronized   void   doSomething() {
        System.out.println( "father.doSomething()" );
    }
}