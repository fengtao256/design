package com.example.springbootdemo.current;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Day03.java
 * @Description 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * @createTime 2020年12月25日 09:50:00
 */
public class Day03 {

    public static void main(String[] args) {
        System.out.println("测试 { "+ firstUniqChar("") +" }");
        System.out.println( findContentChildren(new int[]{1,2},new int[]{1,2,3} ) );
        System.out.println(lengthOfLongestSubstring("aabaab!bb"));
        System.out.println(reverse(-2147483648));
        System.out.println(reverse2(-2147483648));
    }

    public static int reverse2(int x) {
        int rev = 0;
        while( x!= 0){
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static int reverse(int x) {
        String num = new StringBuffer(String.valueOf(x)).reverse().toString() ;
        if( num.endsWith("-") ){
            num = "-"+num.substring(0,num.length()-1) ;
        }
        if(Long.valueOf(num) <= Integer.MAX_VALUE && Long.valueOf(num) >= Integer.MIN_VALUE )
            return Integer.valueOf(num) ;
        else return 0 ;
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length() ;
        if(n <= 1) return n ;
        //寻找不重复最长子串长度，滑动窗口
        //用滑动窗口更新最大长度
        boolean flip = false ;
        String sub = "" ;
        int max = 0 ;
        //窗口开始
        for( int i = 0 ; i < n ; i++ ){
            if( sub.contains( String.valueOf(s.charAt(i)) ) ){
                //滑动窗口
                sub = sub.substring(sub.indexOf(s.charAt(i))+1) ;
            }
            sub += s.charAt(i) ;
            System.out.println(sub);
            max = Math.max(max,sub.length()) ;
        }
        return max ;
    }

    public static int firstUniqChar(String s) {
        //97-122 ，重复则肯定大于122
        Map<Character,Integer> map = new HashMap<>(16) ;
        for( int i = 0 ; i < s.length() ; i++ ){
            if(map.containsKey(s.charAt(i)))
                map.put(s.charAt(i), -1 ) ;
            else
                map.put(s.charAt(i), i ) ;
        }
        int first = -1 ;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int res = entry.getValue() ;
            if( res != -1 && (first > res || first == -1)) {
                first = res;
            }
        }
        return first ;
    }

    /**
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
     * 并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i
     * ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(s);
        Arrays.sort(g);
        int index = 0 ;
        int count = 0 ;
        for(int i = 0 ; i < g.length ; i++){
            //分发蛋糕，以及分发的就不能再用了，index++
            for( int j = index ; j < s.length ; j++){
                //刷新这是分配到的第几个蛋糕
                index = j+1 ;
                if( g[i] <= s[j]){
                    count++ ;
                    break ;
                }
            }
            //蛋糕用完了
            if(index >= s.length)
                break ;
        }
        return count ;
    }
}
