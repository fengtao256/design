package com.example.springbootdemo.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test6.java
 * @Description TODO
 * @createTime 2021年01月27日 14:54:00
 */
@Slf4j(topic = "c.Test6")
public class Test6 {


    int a = 0 ;
    static final Object lock = new Object() ;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            log.debug("开始执行");
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("结束执行执行");

            }
        },"T1").start();
        Thread t = new Thread(() -> {
            log.debug("开始执行");
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("结束执行执行");

            }
        },"T2");
//        t.setDaemon(true);
        t.start();
        Thread.sleep(10);
        synchronized (lock){
            lock.notify();
        }
    }

}
