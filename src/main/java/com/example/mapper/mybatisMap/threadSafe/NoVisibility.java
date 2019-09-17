package com.example.mapper.mybatisMap.threadSafe;

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while(!ready)
                //Thread.yield();方法使得当前线程从执行状态（运行状态）变为可执行态（就绪状态）。cpu会从众多的可执行态里选择，即刚刚的那个线程还是有可能会被再次执行到的，而不是说一定会执行其他线程或者改线程在下一次中不会执行到了。
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
