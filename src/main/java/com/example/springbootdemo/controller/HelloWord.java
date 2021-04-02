package com.example.springbootdemo.controller;

import com.example.springbootdemo.websocket.WebSocket;
import com.mysql.cj.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/helloWord")
public class HelloWord {

    //检测用户的信息前缀detection
    private static final String DETECTION_PRE_KEY = "id:" ;
    //检测用户的信息前缀detection
    private static final String DETECTION_PRE_SET_KEY = "detection" ;

    @ResponseBody
    @RequestMapping(value = "/test")
    public Map<String,Object> testHello(HttpServletRequest request, HttpServletResponse response){
        Map<String ,Object> map = new HashMap<>(16) ;

        map.put("code","200");
        map.put("msg","OK") ;
        map.put("data","测试返回成功");
        return map;
    }
    /**
     * 推送最新的检测信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pushDetail")
    public Map<String, Object> getDetectionInfo(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> retMap = new HashMap<>(10) ;
        //患者的微信openID
        String detail = request.getParameter("detail") ;
        // 需要绑定的设备ID
        String deviceid = request.getParameter("deviceid") ;
        if( !StringUtils.isEmptyOrWhitespaceOnly(detail) ||
                !StringUtils.isEmptyOrWhitespaceOnly(deviceid)){
            String key = DETECTION_PRE_KEY+deviceid ;
            if( RedisUtil.getJedis().hset(DETECTION_PRE_SET_KEY,key,detail) > 0 ){
                //永不过期过期,新的消息来了，直接发送新的消息
                try {
                    WebSocket.sendMessageAll(detail);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                RedisUtil.getJedis().expire(key, -1) ;
                retMap.put("retcode" , "01");
                retMap.put("retmsg", "绑定成功！") ;
            }else{
                retMap.put("retcode" , "00");
                retMap.put("retmsg", "绑定失败，请重试！") ;
            }
        }else{
            retMap.put("retcode" , "00");
            retMap.put("retmsg", "绑定信息不完整，绑定失败！") ;
        }
        return retMap;
    }
}
