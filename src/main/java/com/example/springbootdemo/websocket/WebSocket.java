package com.example.springbootdemo.websocket;

import com.example.springbootdemo.controller.RedisUtil;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket")
public class WebSocket {

  //检测用户的信息前缀detection
  private static final String DETECTION_PRE_SET_KEY = "detection" ;

  private static int onlineCount = 0;
  private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
  private Session session;
  private String username;

  @OnOpen
  public void onOpen(Session session) throws IOException {
    System.out.println("新的连接");
    this.username = onlineCount+"" ;
    this.session = session;
    addOnlineCount();
    clients.put(username, this);
    System.out.println("已连接");
    //主动推送初始消息信息
    Map<String, String> data = RedisUtil.getJedis().hgetAll(DETECTION_PRE_SET_KEY) ;
    sendMessageAll(data.toString()) ;
  String s = "{\"DistributionTime\":1615800205,\"data\":{\"DeviceIdentity\":\"867435053817417\",\"Accuracy\":50.0," +
          "\"Angle\":0.0,\"UserId\":\"CG1BC0ILl1Ub7ehYPzYr/Q==\",\"Latitude\":30.573012573739643,\"Time\":1615800152," +
          "\"Longitude\":104.00648975821326,\"Sp\":0.0,\"Source\":\"Wifi\",\"Altitude\":0.0}}" ;
  }

  @OnClose
  public void onClose() throws IOException {
    clients.remove(username);
    subOnlineCount();
  }

  @OnMessage
  public void onMessage(String message) throws IOException {

//        JSONObject jsonTo = JSONObject.parseObject(message);
//        String mes = (String) jsonTo.get("message");
//        if (!jsonTo.get("To").equals("All")){
//            sendMessageTo(mes, jsonTo.get("To").toString());
//        }else{
      System.out.println("-----------"+message);
      sendMessageAll(message);
//        }
  }

  @OnError
  public void onError(Session session, Throwable error) {
    error.printStackTrace();
  }

  public void sendMessageTo(String message, String To) throws IOException {
    // session.getBasicRemote().sendText(message);
    //session.getAsyncRemote().sendText(message);

    for (WebSocket item : clients.values()) {
      System.out.println(item.username+"===user==="+To );
      if (item.username.equals(To) )
        item.session.getAsyncRemote().sendText(message);
    }
  }

  public static void sendMessageAll(String message) throws IOException {
    for (WebSocket item : clients.values()) {
      System.out.println("-----------"+message);
      item.session.getAsyncRemote().sendText(message);
    }
  }

  public static synchronized int getOnlineCount() {
    return onlineCount;
  }

  public static synchronized void addOnlineCount() {
    WebSocket.onlineCount++;
  }

  public static synchronized void subOnlineCount() {
    WebSocket.onlineCount--;
  }

  public static synchronized Map<String, WebSocket> getClients() {
    return clients;
  }

}
