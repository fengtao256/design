package com.example.springbootdemo.builder;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Client.java
 * 建造者模型，需要一个抽象建造器，建造属性是产品，抽象方法是各个部分的建造方法
 * 实现抽象建造器，实现各个部分的建造方法，子类能继承父类属性，需要将属性设置为protected
 * 指挥者，指挥产品的建造顺序，构造方法中应该传入建造器对象（父类引用指向子类对象），依照顺序构造产品信息后；
 * 调用产品类的getResult方法获取构造好的复杂对象
 * @Description TODO
 * @createTime 2020年12月01日 10:56:00
 */
public class Client {

    public static void main(String[] args) {

        Builder builder = new ConcreteBuilder() ;

        Director director = new Director(builder) ;

        Product product = director.construct() ;

        System.out.println("构件完毕 "+product.toString());

    }
}
