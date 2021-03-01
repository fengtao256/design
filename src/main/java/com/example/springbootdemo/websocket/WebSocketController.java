//package com.example.springbootdemo.websocket;
//
//import org.java_websocket.client.WebSocketClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class WebSocketController {
//
//    @Autowired
//    private WebSocketClient webSocketClient;
//
//    @RequestMapping("subscribe")
//    public String subscribe() {
//        webSocketClient.connect();
//        webSocketClient.send("hello sever，i want subscribe data A");
//        return "发送订阅成功！！！";
//    }
//}