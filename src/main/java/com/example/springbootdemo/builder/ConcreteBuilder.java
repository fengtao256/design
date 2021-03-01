package com.example.springbootdemo.builder;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ConcreteBuilder.java
 * @Description TODO
 * @createTime 2020年12月01日 10:51:00
 */
public class ConcreteBuilder extends Builder {

    @Override
    public void setPartA() {
        product.setPartA("构件A");
    }

    @Override
    public void setPartB() {
        product.setPartB("构件B");
    }

    @Override
    public void setPartC() {
        product.setPartC("构件C");
    }
}
