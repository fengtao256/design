package com.example.springbootdemo.websocket;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        //用于计数客户端数量
        int index = 0 ;
        //提示信息
        System.out.println("我是Server端！");
        //创建一个ServerSocket
        //创建一个ServerSocket
        ServerSocket ss = new ServerSocket(8080) ;

        //不停的等待客户端的链接
        while(true){
            //接受请求
            Socket s = ss.accept();
            //计数累加
            index++;
            //打印
            System.out.println("第 "+index+" 个连接成功！");
            //创建输入流
            DataInputStream in = new DataInputStream(s.getInputStream());
            //打印从客户端发来的消息
            System.out.println(in.readUTF()+"  "+"\n----Port:"+s.getPort());
            //关闭流
            in.close();
        }
    }
}