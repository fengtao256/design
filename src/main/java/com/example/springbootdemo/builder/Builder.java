package com.example.springbootdemo.builder;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Builder.java
 * @Description 抽象方法，抽象每个部分的建造方法
 * @createTime 2020年12月01日 10:48:00
 */
abstract class Builder {

    protected Product product = new Product();
    public abstract  void setPartA() ;
    public abstract  void setPartB() ;
    public abstract  void setPartC() ;
    public Product getResult(){
        return product ;
    }

}