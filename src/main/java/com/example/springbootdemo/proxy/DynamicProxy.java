package com.example.springbootdemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DynamicProxy.java
 * @Description TODO
 * @createTime 2020年12月01日 17:28:00
 */
public class DynamicProxy implements InvocationHandler {

    private Object object = null ;

    public DynamicProxy(Object object){
        this.object = object ;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 代理方法
     * @param args 方法参数信息
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("{ 你好，动态代理！}");
        method.invoke(object,args) ;
        return null;
    }
}
