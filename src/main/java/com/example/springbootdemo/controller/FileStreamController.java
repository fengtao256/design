package com.example.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/fileStream")
public class FileStreamController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public Map<String,Object> setFileStream(HttpServletRequest request, HttpServletResponse response) throws Exception{
        File file = new File("D:/wrss.sql") ;
        Map<String,Object> retMap = new HashMap<String,Object>();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            int length = inputStream.read(data);
            inputStream.close();
//            response.setContentType("text/plain;charset=utf-8");
//            OutputStream stream = response.getOutputStream();
//            stream.write(data);
//            stream.flush();
//            stream.close();
//            System.out.println("文件流信息成功发送至response"+stream.toString());
//            response.setStatus(200);//返回状态码
            retMap.put("code", "300") ;
            retMap.put("processname", "processid") ;
            retMap.put("productCreateTime", "processid") ;
            return retMap ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retMap ;
    }
}
