package com.example.springbootdemo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test7.java
 * @Description java设计模式，保护性暂停
 * 简要分析下面程序如何保证多线程的线程安全
 * 1、GuardedObject 是信息
 * 2、Postman 邮递员
 * 3、Person 收件人
 * 4、Mailboxes 信箱
 *
 * @createTime 2021年01月27日 15:18:00
 */
@Slf4j(topic = "c.test8")
public class Test7 {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 4; i++) {
            new Person().start();
        }
        Thread.sleep(4000);
        Set<Integer> ids = Mailboxes.getIds() ;
        for (Integer id : ids ) {
            new Postman(id,"信息{"+id+"}").start() ;
        }
    }
}

@Slf4j(topic = "person")
class Person extends Thread{
    @Override
    public void run() {
        GuardedObject go = Mailboxes.createGuardedObject() ;
        log.debug("等待收信:[{}]",go.getId());
        Object mail = go.getResponse(5000) ;
        log.debug("收到信:[{}],内容:[{}]",go.getId(),mail);
    }
}

@Slf4j(topic = "postman")
class Postman extends Thread{

    private int id ;
    private String mail ;

    public Postman(int id , String mail){
        this.id = id ;
        this.mail = mail ;
    }

    @Override
    public void run() {
        GuardedObject go = Mailboxes.getGuardedObject(this.id);
        log.debug("开始送信:[{}],内容:[{}]",this.id,this.mail);
        go.complete(mail);
    }
}

/**
 * 信箱
 */
class Mailboxes {

    /**
     * 信箱里面放置了信件信息，当有信息反馈时，收件人才响应
     */
    private static Map<Integer,GuardedObject> boxes = new Hashtable<>() ;
    /**
     * 由信箱自动生成Id号码
     */
    private static int id ;

    private static synchronized int generateId(){
        return id++ ;
    }

    public static GuardedObject createGuardedObject(){
        GuardedObject guardedObject = new GuardedObject(generateId()) ;
        boxes.put(guardedObject.getId(),guardedObject) ;
        return guardedObject ;
    }

    public static GuardedObject getGuardedObject(int id){
        return boxes.remove(id) ;
    }

    public static Set<Integer> getIds(){
        return boxes.keySet() ;
    }

}

/**
 * 消息，通过互斥锁实现了线程间通信，一个线程等待另一个线程的结果
 */
@Slf4j(topic = "c.test7")
class GuardedObject{

    /**
     *     标识 GuardedObject
     */
    private int id ;

    private Object response ;

    public GuardedObject(int id){
        this.id = id ;
    }

    public int getId() {
        return id;
    }

    public Object getResponse(long timeout){
        synchronized (this){
            long begin = System.currentTimeMillis() ;
            long now = 0 ;
            //设置超时等待，同时防止虚假唤醒
            while(response == null){
                long delay = timeout-now ;

                if(delay <= 0){
                    break ;
                }

                try {
                    this.wait(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                now = System.currentTimeMillis()-begin ;
            }
            return response ;
        }
    }

    public void complete(Object obj){
        //临界区
        synchronized (this) {
            this.response = obj;
            this.notifyAll();
        }
    }
}