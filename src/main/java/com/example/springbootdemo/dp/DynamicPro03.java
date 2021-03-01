package com.example.springbootdemo.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DynamicPro01.java
 * @Description 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1）
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1]
 * 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * @createTime 2020年12月16日 10:19:00
 */
public class DynamicPro03 {

    public static void main(String[] args) {
        System.out.println(cuttingRope2(6)) ;
    }

    public int cuttingRope1(int n) {
        //设置长度为dp[n]是长度为n的绳子剪过后构成的最大乘积
        //设置最后再i位置剪断，则dp[n]=dp[n-k[i]]*k[i],
        //dp[0] = 0 ;
        //dp[1] = 0 ; 1X0
        //dp[2] = 1 ; 1X1
        //dp[3] = 2 ; 1X2
        //dp[4] = 4 ; 2X2
        //dp[5] = 6 ; 2X3
        //dp[6] = 9 ; 3X3
        // 倍数
        int k = n/3 ;
        //余数
        int y = n%3 ;
        if(y==1)
            return (int) Math.pow(3,k-1)*4;
        if(y ==2)
            return (int) (Math.pow(3,k)*2);
        return (int) Math.pow(3,k);
    }

    public static int cuttingRope2(int n) {
        //设置长度为dp[n]是长度为n的绳子剪过后构成的最大乘积
        //设置最后剪断长度，则dp[n]=Math.mxax(dp[n-k]*k,k*(n-k)),剩下的剪断绳子或者不剪断
        // 倍数
        int[] dp = new int[n+1] ;
        //初始化值
        //dp[0] = dp[1] = 0 ;
        for(int i = 2 ; i <= n  ; i++){
            //最后一段不同长度下的乘积计算，当j为1的时候，计算结果为0，可以直接略过
            for(int j = 1 ; j < i  ; j++){
                //dp[i]是已经计算出的结果
                dp[i] = Math.max( Math.max((i-j)*j,dp[i-j]*(j)),dp[i] ) ;
            }
        }
        return dp[n] ;
    }

}
