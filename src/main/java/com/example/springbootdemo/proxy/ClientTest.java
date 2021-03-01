package com.example.springbootdemo.proxy;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.entity.FileUtils;
import com.example.springbootdemo.entity.NLPLabelsEntity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ClientTest.java
 * @Description TODO
 * @createTime 2020年12月01日 16:20:00
 */
public class ClientTest {
    public static void main(String[] args) {
//        ProxyTest proxy = new ProxyTest() ;
//        proxy.display();
//        System.out.println(proxy.getClass());

        //抽象接口
        Subject subject = null ;
        //处理器
        InvocationHandler invocationHandler = null ;
        invocationHandler = new DynamicProxy(new RealSubject()) ;
        subject = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(),
                                                   new Class[]{Subject.class},
                                                   invocationHandler);
        subject.display();
        System.out.println("代理对象是："+subject.getClass());
//        long start = System.currentTimeMillis() ;
//        System.out.println("======");
//        //转成json字符串
//        String path = "D:\\complay-file\\公司项目\\气象灾害知识库项目\\系统开发\\test_text0-txt.json" ;
////        String json = JSON.toJSON(FileUtils.ReadFile( path )).toString();
////        System.out.println(json);
//        //json字符串转成对象
//        StringBuffer stringBuffer = new StringBuffer() ;
//        stringBuffer.append( FileUtils.ReadFile( path ) ) ;
////        FileUtils.ReadFile( path ) ;
//        //NLPLabelsEntity test = JSON.parseObject(FileUtils.ReadFile( path ),NLPLabelsEntity.class);
//        long end = System.currentTimeMillis() ;
////        System.out.println(stringBuffer);
//        System.out.println("======加载转换耗时"+(end-start)+"ms");
    }
}
