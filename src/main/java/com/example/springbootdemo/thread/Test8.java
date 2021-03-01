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
        System.out.println("qn_1".split("qn_")[1]);
        MessageQueue messageQueue = new MessageQueue(10) ;
        for (int i = 0; i < 30; i++) {
            int id = i ;
            new Thread(() -> messageQueue.put(new Message(id,"产品"+id)) ).start();
        }
        new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                messageQueue.take() ;
            }
        }).start();
    }
}

/**
 * 消息队列
 */
@Slf4j(topic = "消息队列")
class MessageQueue{

    /**
     * 消息队列,用双向链表实现
     */
    private Queue<Message> queue = new LinkedList<>() ;
    private int capacity ;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }
    /**
     * 生产消息
     */
    public void put(Message message){
        //临界区，保护queue队列线程安全
        synchronized (queue) {
            while (queue.size() >= capacity){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //生产一个消息，则唤醒消费者
            queue.offer(message) ;
            queue.notifyAll();
            log.debug("生产消息：{},现有库存:{}",message.toString(),queue.size() );

        }
    }

    /**
     * 消费消息
     * @return
     */
    public Message take(){
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //消费一个消息，需要唤醒生产者
            Message message = queue.poll() ;
            log.debug("消费消息：{},现有库存:{}",message.toString(),queue.size() );
            queue.notifyAll();
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