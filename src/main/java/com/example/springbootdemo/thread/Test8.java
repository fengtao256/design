package com.example.springbootdemo.thread;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test8.java
 * @Description TODO
 * @createTime 2021年01月28日 13:20:00
 */
public class Test8 {

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(10) ;
        //源源不断的生产线程
        new Thread(() -> {
            int id = 0 ;
            while( true){

                messageQueue.put(new Message(++id, "产品" + id)) ;
            }
        }).start();
        //永不停息的消费线程
        new Thread(() -> {
            while(true){
                messageQueue.take() ;
            }
        }).start();
    }
}

/**
 * 消息队列
 * 弊端，是添加的互斥锁
 * 当生产者生产消息时，消费者不能消费消息
 * 而消费者消费消息时，生产者不能生产消息
 * 从而影响了生产效率
 */
@Slf4j(topic = "消息队列")
class MessageQueue{

    /**
     * 消息队列,用双向链表实现
     */
    private Queue<Message> queue  ;
    private int capacity ;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
        //懒加载，当多线程实现的时候
        queue = new LinkedList<>() ;
    }
    /**
     * 生产消息
     */
    public void put(Message message){
        //临界区，保护queue队列线程安全
        synchronized (this) {
            while (queue.size() >= capacity){
                try {
                    //释放了锁对象，并且当前线程进入了等待状态
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //生产一个消息，则唤醒消费者
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.offer(message) ;
            this.notifyAll();
            log.debug("生产消息：{},现有库存:{}",message.toString(),queue.size() );

        }
    }

    /**
     * 消费消息
     * @return
     */
    public Message take(){
        synchronized (this) {
            while (queue.isEmpty()) {
                try {
                    //释放了锁对象，并且当前线程进入等待状态
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //消费一个消息，需要唤醒生产者
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = queue.poll() ;
            log.debug("消费消息：{},现有库存:{}",message.toString(),queue.size() );
            this.notifyAll();
            return message ;
        }
    }
}

/**
 * 消息类,
 */
final class Message{
    private int id ;
    private Object message ;

    public Message(int id, Object message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public Object getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message=" + message +
                '}';
    }

}
