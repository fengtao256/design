package com.example.springbootdemo.proxy;

import java.lang.reflect.Proxy;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProxyTest.java
 * @Description TODO
 * @createTime 2020年12月01日 16:14:00
 */
public class ProxyTest implements Subject {

    private RealSubject realSubject = new RealSubject() ;

    @Override
    public void display() {
        preDisplay() ;
        realSubject.display();
        postDisplay();

    }
    private void preDisplay(){
        System.out.println("调用之前");
    }
    private void postDisplay(){
        System.out.println("调用之后");
    }
}
