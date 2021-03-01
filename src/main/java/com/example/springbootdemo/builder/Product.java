package com.example.springbootdemo.builder;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Product.java
 * @Description TODO
 * @createTime 2020年12月01日 10:47:00
 */
public class Product {

    private String partA ;

    private String partB ;

    private String partC ;

    public void setPartA(String partA) {
        this.partA = partA;
        System.out.println("构件了【A】部分");
    }

    public void setPartB(String partB) {
        this.partB = partB;
        System.out.println("构件了【B】部分");
    }

    public void setPartC(String partC) {
        this.partC = partC;
        System.out.println("构件了【C】部分");
    }

    @Override
    public String toString() {
        return "Product{" +
                "partA='" + partA + '\'' +
                ", partB='" + partB + '\'' +
                ", partC='" + partC + '\'' +
                '}';
    }
}
