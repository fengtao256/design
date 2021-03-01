package com.example.springbootdemo.entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.mysql.cj.util.StringUtils;
//import com.spire.doc.Document;

public class FileUtils {
	

    
	/**
	 * 
	* @Title: ReadFile
	* @Description: 按行读取文件
	* @author: FengTao
	* @date 2020年9月8日 上午11:29:22
	* @param Path
	* @return String
	* @version
	 */
	public static String ReadFile(String Path){
//        BufferedReader reader = null;

        StringBuffer info = new StringBuffer();
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);

            FileChannel channel = fileInputStream.getChannel();
            int capacity = 10485760;// 字节，初始化为10M
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            System.out.println("限制是：" + bf.limit() + ",容量是：" + bf.capacity() + " ,位置是：" + bf.position());
            int length = -1;
            while ((length = channel.read(bf)) != -1) {
                bf.clear();
                byte[] bytes = bf.array();
//                System.out.println("start..............");
                String str = new String(bytes, 0, length);
//                System.out.println(str);
                info.append(str) ;
                //System.out.write(bytes, 0, length);

//                System.out.println("end................");

//                System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity() + "位置是：" + bf.position());

            }
            channel.close();



////            String fileCode = EncodingDetect.getJavaEncode(Path);
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//            reader = new BufferedReader(inputStreamReader);
//            String tempString = null;
//            while((tempString = reader.readLine()) != null){
//                laststr += tempString;
//            }
//            reader.close();
        }catch(IOException e){
            //e.printStackTrace();
        	System.out.print("找不到文件"+Path);
        }finally{
//            if(reader != null){
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        return info.toString();
    }


}
