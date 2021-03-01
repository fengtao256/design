package com.example.springbootdemo.controller;

import com.sun.deploy.net.HttpUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping(value = "/fileStream")
public class DownLoadTest {

    @RequestMapping(value = "/getStream")
    public void DownLoadTest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        URL url = new URL("http://localhost:8080/fileStream/test");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod("POST"); // 设置请求方式
        connection.setReadTimeout(600000);//设置接口响应时间为10分钟
        connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
        connection.connect();

        //传递JSON参数
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        out.flush();
        out.close();
        if(connection.getResponseCode()==200){
            InputStream br = connection.getInputStream();
//
//            int line;
//            byte[] bytes = new byte[1024];
//            FileOutputStream downloadFile = new FileOutputStream("D:/wrss.txt");
//            while((line = br.read(bytes)) != -1){
//                //System.out.println(line);
//                downloadFile.write(bytes, 0, line);
//                downloadFile.flush();
            BufferedImage bufferedImage = ImageIO.read(br);
            ImageIO.write(bufferedImage , "png" , new File("D:/wrss.png"));
            br.close();
            System.out.println("文件流存入成功！");
//            downloadFile.close();
//            br.close();
        }else{
            System.out.println("没有数据");
            response.setStatus(300);
        }
    }

}
