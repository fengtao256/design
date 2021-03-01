package com.example.springbootdemo.current;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName day02.java
 * @Description TODO
 * @createTime 2020年12月22日 16:16:00
 */
public class day02 {
    private static int answer = 0 ;

    public static void main(String[] args) {
        TreeNode root = new TreeNode() ;
        root.val = 1 ;
        root.left = null ;
        root.right = new TreeNode(3) ;
        String regEx="[-:：/-]";
        String str = "12:11/13-10-:121";
        String newString = str.replaceAll(regEx,"");

        SimpleDateFormat origin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat  sdf =
                sdf = new SimpleDateFormat("HHmm");
        try {
            System.out.println("时间 ： "+sdf.format(origin.parse("1899-12-31 16:15:30")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        System.out.println(maxDepth(root));
        System.out.println(
                lastStoneWeight( new int[]{2,7,4,1,8,1}) );



    }

    public  static int maxDepth(TreeNode root) {
//        int depth = 0 ;
//        getDepth(root , depth) ;
//        return answer ;
        if(root == null) {
            return 0;
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }
    public static void getDepth(TreeNode root , int depth) {
        if(root != null){
            depth++ ;
            answer = Math.max( depth ,answer ) ;
        }
        if( root.left != null )
            getDepth(root.left , depth) ;
        if( root.right != null )
            getDepth(root.right , depth) ;
    }

    public static int lastStoneWeight(int[] stones) {
        int n = stones.length ;
        if( n == 0) return 0 ;
        if( n == 1) return stones[1] ;
        Arrays.sort(stones) ;
        int remain = 0 ;
        for(int i = 1 ; i < n ; i ++ ){
        }
        return remain ;
    }
}
