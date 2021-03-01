package com.example.springbootdemo.current;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TreeNode.java
 * @Description TODO
 * @createTime 2020年12月22日 16:17:00
 */
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}
