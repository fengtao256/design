package com.example.springbootdemo.websocket;

import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String[] args) throws Exception{
        //提示信息
        System.out.println("我是客户端！");
        //创建一个Socket类用于连接服务器端
        Socket s = new Socket("127.0.0.1",8080) ;
        //创建一个输出流
        OutputStream out = s.getOutputStream();
        //写数据到服务器端
        new DataOutputStream(out).writeUTF("----hello , I am Client !");
        s.close();

    }
}
