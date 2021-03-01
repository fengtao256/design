package com.example.springbootdemo.websocket01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fengtao256
 */
@Slf4j
@Controller
@RequestMapping(value = "/websocket")
public class SocketTestApi {
    @ResponseBody
    @RequestMapping(value="/pushVideoListToWeb",method= RequestMethod.POST,consumes = "application/json")
    public Map<String,Object> pushVideoListToWeb(@RequestBody Map<String,Object> param ) {
        Map<String,Object> result =new HashMap<>(16);
        try {
//            log.info("当前在线人数"+WebSocketServer.getOnlineCount());
//            WebSocketServer.sendInfo("收到来自【"+param.get("userName")+"】的消息："+param.get("message"));
            sendWhile10s() ;
            result.put("operationResult", true);
        }catch (Exception e) {
            result.put("operationResult", true);
        }
        return result;
    }
    public static void sendWhile10s() {
        while(true){
            try {
                Thread.sleep(5000);
                log.info("当前在线人数"+WebSocketServer.getOnlineCount());
                WebSocketServer.sendInfo("收到来自【系统】的消息：这是一条定时心跳消息");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
