package com.example.springbootdemo.builder;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Director.java
 * @Description TODO
 * @createTime 2020年12月01日 10:52:00
 */
public class Director {

    private Builder builder ;
    public Director(Builder builder){
        this.builder = builder ;
    }

    public Product construct(){
        builder.setPartA();
        builder.setPartB();
        builder.setPartC();
        return builder.getResult() ;
    }
}
