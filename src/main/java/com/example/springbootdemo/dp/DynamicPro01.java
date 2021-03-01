package com.example.springbootdemo.dp;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DynamicPro01.java
 * @Description TODO
 * @createTime 2020年12月16日 10:19:00
 */
public class DynamicPro01 {

    public static void main(String[] args) {
        System.out.println("总数是： "+coins(new int[]{2,5,7},11));
    }

    /**
     * 集齐硬币问题(动态规划),找出最少的硬币组合数
     * 1、确定最后一步，转化为子问题，集齐n块钱，需要k枚硬币(面值为2/5/7)，则f(n) = f(n-2）+1 或者 f(n-5)+1 或者 (n-7)+1 ,得到最小值就是最后一枚硬币的值
     * 2、转移方程 f(n) = Math.max( f(n-2)+1 , f(n-5)+1 , f(n-7)+1 ) ;
     * 3、初始条件 f(0) = 0 ; f(-1) = Integer.Max_Value
     * 4、计算顺序 自下而上
     * @return
     */
    public static int coins(int[] A,int M){
        // 最后是获取f[M]的值，所以声明M+1的长度
        int[] f = new int[M+1] ;
        //初始化值
        f[0] = 0 ;
        for (int i = 1; i <= M; i++) {
            //初始化为最大值,因为是寻找最小值
            f[i] = Integer.MAX_VALUE ;
            //优化成for循环
            for (int j = 0; j < A.length; j++) {
                //最小只能计算f[i]为f[0] ,i<0时不做计算,而且要保证f[i-A[j]]能拼接出来
                if (i >= A[j] && f[i - A[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i - A[j]] + 1);
                }
            }
        }
        return f[M] == Integer.MAX_VALUE ? -1 : f[M];
    }
}
