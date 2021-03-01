package com.example.springbootdemo.current;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fengtao256
 */
public class Day01 {

    public static void main(String[] args) {

        int testNum = 10 ;
        //备忘录
        long[] members = new long[testNum+1] ;
        for (int i =0 ; i< members.length ; i++) {
            members[i] = -1 ;
        }
        long startTime=System.currentTimeMillis();
        System.out.println();
        //原始斐波拉切数列
        System.out.println(fibonacci(testNum));

        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+Double.valueOf(endTime-startTime)/1000+"s");


        //优化斐波拉切数列
        System.out.println(fibonacci02(testNum,members));
        //获取结束时间
        long endTime02=System.currentTimeMillis();
        System.out.println("程序运行时间： "+Double.valueOf(endTime02-endTime)/1000+"s");
    }

    public static long fibonacci(int number){
        if( number ==1 || number ==2)
            return 1;
        if( number <= 0 )
            return 0 ;
        return fibonacci(number-1)+fibonacci(number-2) ;
    }

    public static long fibonacci02(int number,long[] members){
        if(members[number] >= 0)
            return members[number] ;
        if( number == 2 || number==1) {
            members[number] = 1;
        }else if(number <= 0){
            members[number] = 0;
        } else{
            members[number] = fibonacci02(number-1, members) + fibonacci02(number-2, members) ;
        }
        return members[number] ;
    }

}
