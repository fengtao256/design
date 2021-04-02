package com.example.springbootdemo.thread;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.AccessibleObject;
import java.util.Random;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test5.java
 * @Description TODO
 * @createTime 2021年01月26日 15:06:00
 */
@Slf4j
public class Test5 {

    public static void main(String[] args) throws InterruptedException {
        Acount a = new Acount(1000) ;
        Acount b = new Acount(1000) ;
        Acount c = new Acount(1000) ;

        Thread threada = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b,getRandomInt());
            }
        }) ;

        Thread threadb = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a,getRandomInt());
            }
        }) ;

        Thread threadc = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                c.transfer(a,getRandomInt());
                c.transfer(b,getRandomInt());
            }
        }) ;
        threada.start();
        threadb.start();
        threada.join();
        threadb.join();
        log.debug("total -> {}",a.getMoney()+b.getMoney()+c.getMoney());
        log.debug("money b-> {}",b.getMoney());
        log.debug("money a-> {}",a.getMoney());
        log.debug("money c-> {}",c.getMoney());
    }

    public static int getRandomInt(){
        return new Random().nextInt(5)+1 ;
    }

}

/**
 * 账户类
 */
class Acount{
    //共享变量
    private int money ;

    public Acount(int money) {
        this.money = money ;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money){
        this.money = money ;
    }
    //转账
    public void transfer(Acount target , int amount){
        synchronized (Acount.class) {
            if (this.money >= amount && amount > 0) {
                //转账放减钱
                this.setMoney(this.getMoney() - amount);
                //收账放获取钱
                target.setMoney(target.getMoney() + amount);
            }
        }
    }
}
