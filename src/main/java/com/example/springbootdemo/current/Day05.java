package com.example.springbootdemo.current;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Day03.java
 * @Description
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * @createTime 2020年12月25日 09:50:00
 */
public class Day05 {

    public static void main(String[] args) {
        System.out.println(new Day05().eraseOverlapIntervals(new int[][]{ {1,100},{1,50} }));
        System.out.println(new Day05().maximumProduct(new int[]{-1,-2,-3,-4}));
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if( intervals.length <= 1 ) return 0 ;
        //贪心算法，局部最优
        //1、排序,先左端点排序，再按照右端点排序
        Arrays.sort( intervals , Comparator.comparingInt(x -> x[0]) ) ;
        int start = intervals[0][0] ;
        int end = intervals[0][1] ;
        int count = 0 ;
        for(int i = 1 ; i < intervals.length ; i++){
            //下一个区间在这个区间之内，则标记去除
            if(intervals[i][0] >= start && intervals[i][0] < end){
                end = Math.min( end , intervals[i][1] ) ;
                count++ ;
                continue;
            }else{
                start = intervals[i][0] ;
                end = intervals[i][1] ;
            }
        }
        return count ;
    }
    public int maximumProduct(int[] nums) {
        if(nums == null || nums.length <= 0) return 0 ;
        int temp = 0 ;
        for(int i = 0 ; i<nums.length ; i++){
            for(int j = i+1 ; j<nums.length ; j++){
                if(nums[i] > nums[j]){
                    temp = nums[i] ;
                    nums[i] = nums[j] ;
                    nums[j] = temp ;
                }
            }
        }
        int n = nums.length ;
        System.out.println("00000---"+nums[1]*nums[0]*nums[nums.length-1]);
        System.out.println(nums[n-1]);
        System.out.println(nums[n-2]);
        System.out.println(nums[n-3]);
        System.out.println("00001---"+nums[n-1]*nums[n-2]*nums[n-3]);
        int num = 0 ;
        int num1 = nums[0] ;
        int num2 = nums[1] ;
        int num0 = nums[2] ;
        int num3 = nums[nums.length-3] ;
        int num4 = nums[nums.length-2] ;
        int num5 = nums[nums.length-1] ;
        if(num5 < 0){
            System.out.println("===");
            num = num1*num2*num0 ;
        }else if(num1 < 0 && num2 < 0 && ( num1*num2 > num3*num4 ) ){

            System.out.println("+++");
            num = num1*num2*num5 ;
        }else{

            System.out.println("---");
            num = num3*num4*num5 ;
        }
        return num ;
    }
}
