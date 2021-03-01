package com.example.springbootdemo.dp;

import java.util.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * @author admin
 * @version 1.0.0 ,买股票的最佳时机 含手续费
 * @ClassName DynamicPro01.java
 * @Description TODO
 * @createTime 2020年12月16日 10:19:00
 */
public class DynamicPro02 {

    public static void main(String[] args) {
        String s = "XYZZ" ;
        char[] chars = s.toCharArray() ;
        Map<Character,Integer> map = new HashMap<Character, Integer>() ;
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
            if(map.containsKey(chars[i])){
                map.put(chars[i],map.get(chars[i])+1) ;
            }else{
                map.put(chars[i],1) ;
            }
        }
        System.out.println(maxProfit(new int[]{1,3,2,8,4,9},2));
    }


    public static int maxProfit(int[] prices, int fee) {
        //最后一步
        //设最后一天不持有股票利润为dp0[i]，持有利润为dp1[i]
        //转移方程
        //则 dp0[i] = max( dp0[i-1] , dp1[i-1]+prices[i] ;
        //则 dp1[i] = max( dp1[i-1] , dp0[i-1]-prices[i]-fee) ;
        int length = prices.length ;
        int[] dp0 = new int[length] ;
        int[] dp1 = new int[length] ;
        //初始化值
        dp0[0] = 0 ;
        dp1[0] = prices[0] > 0 ? -prices[0] : 0;
        for(int i = 0 ; i < length ; i ++ ){
            if( i-1 >= 0){
                dp0[i] = Math.max( dp0[i-1] , dp1[i-1]+prices[i] ) ;
                dp1[i] = Math.max( dp1[i-1] , dp0[i-1]-prices[i]-fee ) ;
            }
        }
        return dp0[length-1] ;
    }
}
