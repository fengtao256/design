package com.example.springbootdemo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Stream;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test4.java
 * @Description TODO
 * @createTime 2021年01月26日 14:32:00
 */
@Slf4j
public class Test4 {

    public static void main(String[] args) throws InterruptedException {
        TicketWindow tw = new TicketWindow(1000) ;
        //线程安全的list
        List<Integer> list = new Vector<>() ;
        //在主线程内，不存在资源竞争，是安全的
        List<Thread> threads = new ArrayList<>() ;
        //如果买票数量和卖盘数量一致，则没有线程安全问题
        for (int i = 0; i < 4000 ; i++) {
            Thread thread = new Thread(() ->{
                //共享变量的读写操作
                int amount = tw.cell(getRandomInt()) ;
                list.add(amount);
            }) ;
            threads.add(thread) ;
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        log.debug("余票数量:{}",tw.getTicketCount());
        log.debug("购票数量:{}", list.stream().mapToInt(Integer::intValue).sum());
    }

    public static int getRandomInt(){
        return new Random().nextInt(5)+1 ;
    }
}

class TicketWindow {

    //余票数量
    private int ticketCount ;

    public TicketWindow(int ticketCount){
        this.ticketCount = ticketCount ;
    }

    //获取余票数量
    public int getTicketCount(){
        return ticketCount ;
    }

    //买票
    public synchronized int cell(int count){
        if(this.ticketCount >= count){
            this.ticketCount -= count ;
            return count ;
        }else{
            return  0 ;
        }
    }
}