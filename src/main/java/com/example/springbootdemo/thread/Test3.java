package com.example.springbootdemo.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test3.java
 * @Description TODO
 * @createTime 2021年01月25日 17:36:00
 */
@Slf4j
public class Test3 {

    static int count;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    count++;
                }
            }

        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    count--;
                }
            }
        });

        t1.start();
        t2.start();
        //主线程等待t1和t2都运行完毕
        t1.join();
        t2.join();
        log.debug("{}", count);
    }
}
