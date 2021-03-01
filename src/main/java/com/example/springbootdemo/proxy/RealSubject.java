package com.example.springbootdemo.proxy;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RealSubject.java
 * @Description TODO
 * @createTime 2020年12月01日 16:15:00
 */
public class RealSubject implements Subject {

    @Override
    public void display() {
        System.out.println("真实主题被调用");
    }

}
