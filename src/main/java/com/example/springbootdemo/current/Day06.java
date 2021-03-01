package com.example.springbootdemo.current;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Day06.java
 * @Description TODO
 * @createTime 2021年01月08日 14:25:00
 */
public class Day06 {
//    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String now = format.format(System.currentTimeMillis()) ;
//        System.out.println(now);
//        String beginTime=new String("2017-06-09 10:22:22");
//        String endTime=new String("2017-05-08 11:22:22");
//        System.out.println(format.parse(beginTime).getTime() > format.parse(endTime).getTime());
//
//        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
//        String begin = "17:10" ;
//        String end = "18:50" ;
//        System.out.println(format2.parse(now));
//    }
public static void main(String args[]) throws IOException {
    char c;
    // 使用 System.in 创建 BufferedReader
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("输入字符, 按下 'q' 键退出。");
    // 读取字符
    do {
        c = (char) br.read();
        System.out.println(c);
    } while (c != 'q');
}
    private static String radomString(){
        String linkNo = "";
        // 用字符数组的方式随机
        String model = "0123456789qwertyuiopasdfghjklzxcvbnm_";
        char[] m = model.toCharArray();
        for (int j = 0; j < 8; j++) {
            char c = m[(int) (Math.random() * 37)];
            linkNo = linkNo + c;
        }
        return linkNo;
    }
}
