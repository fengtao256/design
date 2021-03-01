package com.example.springbootdemo.current;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Day03.java
 * @Description
 * @createTime 2020年12月25日 09:50:00
 */
public class Day04 {

    public static void main(String[] args) {
        System.out.println(new Day04().romanToInt("MCMXCIV"));
    }

    public int romanToInt(String s) {

        int[] a = new int[s.length()] ;
        for(int i = 0 ; i < s.length() ; i++ ){
            switch (s.charAt(i)){
                case 'I' :
                    a[i] = 1 ;
                    break ;
                case 'V' :
                    a[i] = 5 ;
                    break ;
                case 'X' :
                    a[i] = 10 ;
                    break ;
                case 'L' :
                    a[i] = 50 ;
                    break ;
                case 'C' :
                    a[i] = 100 ;
                    break ;
                case 'D' :
                    a[i] = 500 ;
                    break ;
                case 'M' :
                    a[i] = 1000 ;
                    break ;
                default:
                        break ;
            }
            System.out.println(a[i]);
        }
        int sum = 0 ;
        for(int i = 0 ; i < s.length() ; i++ ){
            if( i+1 == s.length() || a[i] >= a[i+1] ) sum += a[i] ;
            else {
                sum += a[i+1]-a[i];
                i++ ;
            }

        }
        return sum ;
    }
}
