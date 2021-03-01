package com.example.springbootdemo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DynamicPro04.java
 * @Description 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *              设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *              注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * @createTime 2020年12月16日 10:19:00
 */
public class DynamicPro04 {

    public static void main(String[] args) {
        System.out.println("总数是： "+new DynamicPro04().maxProfit(1,new int[]{1,2}));
    }

    /**
     * 集齐硬币问题(动态规划),找出最少的硬币组合数
     * 0、翻译问题：在交易次数不多于K的情况下，最大利润为多少,设置最大利润为maxProfit？
     * 则maxProfit为max(profit[0],profit[1],profit[2]...profit[k]) ;
     * 1、确定最后一步，转化为子问题，设置股票在K次交易下达到的最大利润为profit[K] , 则profit[k] = profit[k-1]+
     * 2、转移方程 f(n) = Math.max( f(n-2)+1 , f(n-5)+1 , f(n-7)+1 ) ;
     * 3、初始条件 f(0) = 0 ; f(-1) = Integer.Max_Value
     * 4、计算顺序 自下而上
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int length = prices.length ;
        if( k <= 0 || length <= 1)
            return 0 ;
        //动态规划
        //题目分析：prices[i]数组代表i天股票价格是price[i],k代表最多能交易多少次
        //求解：买卖股票的最大利润? 若k = 2，那么可能交易一次达到的利润最大，也可能交易两次的利润最大？
        //设置持有股票状态为1，不持有状态为0,则每天的持有状态则可以表示出来(设置k卖出才算一次交易)
        //设置dp[i][0][k]为第i天不持有票并且交易次数为k次的利润
        //设置dp[i][1][k]为第i天持有股票并且交易次数为k次的利润
        //最后从dp[i][0][k]里面求解最大值则为求解结果
        //状态转移方程为dp[i][0][k] = Max.max( 前一天持有,前一天操作次数应该为k-1-则今天应该卖出，前一天也不持有) ;
        //                          = Max.max( dp[i-1][1][k-1]+prices[i] , dp[i-1][0][k] ) ;
        //状态转移方程为dp[i][1][k] = Max.max( 前一天就持有，前一天不持有-则今天应该买入) ;
        //                          = Max.max( dp[i-1][1][k] , dp[i-1][0][k]-prices[i]) ;
        //初始值dp[0][0][0] = 0 ;
        //dp[0][1][0] = 0 ;
        int[][][] dp = new int[length+1][k+1][2] ;
        //初始化非法值,合法值默认是0
        for(int j = 0 ; j <= k ; j++){
            dp[0][j][1] = Integer.MIN_VALUE ;
        }
        for(int i = 0 ; i < length ; i++){
            dp[i][0][1] = Integer.MIN_VALUE ;
        }
        //初始化合法值
        dp[1][1][1] = -prices[0] ;
        int maxProfit = 0 ;
        for(int i = 1 ; i <= length ; i++){
            for(int j = 1 ; j <= k ; j++){
                //卖入则算一次交易
                dp[i][j][0] = Math.max( dp[i-1][j][1]+prices[i-1] , dp[i-1][j][0] ) ;
                dp[i][j][1] = Math.max( dp[i-1][j][1] , dp[i-1][j-1][0]-prices[i-1]) ;
                System.out.println("利润："+i+j+0+"==="+dp[i][j][0]);
                maxProfit = Math.max( maxProfit , dp[i][j][0] ) ;
            }
        }
        return maxProfit ;
    }
}
