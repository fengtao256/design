package com.example.springbootdemo.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test1.java
 * @Description 两阶段终止模式
 * @createTime 2021年01月25日 15:50:00
 */
@Slf4j(topic = "c.Test1")
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination termination = new TwoPhaseTermination();
        log.debug("===启动系统中===");
        termination.start();
        log.debug("===启动系统完毕===");
        Thread.sleep(500);
        termination.stop();
    }
}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination {

    private Thread monitor;
    /**
     * start monitor
     */
    public void start() {
        //thread run方法重写，使用函数式表达式
        monitor = new Thread(new Monitor(), "monitor");
        monitor.start();
    }
    /**
     * over monitor
     */
    public void stop() {
        monitor.interrupt();
    }

    class Monitor implements Runnable {

        @Override
        public void run() {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    log.debug("关闭系统前的处理");
                    log.debug("系统安全退出、、、");
                    break;
                }
                try {
                    //睡眠
                    Thread.sleep(100);
                    //记录监控信息
                    log.debug("执行监控任务、、、");
                } catch (InterruptedException e) {
                    log.debug("被打断了、、、");
                    e.getMessage();
                    //重新做标记，因为InterruptedException会清楚打断标记
                    current.interrupt();
                    //随笔：
                    //1、 0.75的负载因子是一个统计后的结果，为了提高hashMap的各项性能，减少hash碰撞的机率
                    //2、为什么是2的倍数呢？hashMap计算hash桶位置的计算方法是：(n - 1) & hash
                    //  当HashMap的容量是2的n次幂时，(n-1)的2进制也就是1111111***111这样形式的，这样与添加元素的hash值进行位运算时，能够充分的散列
                    //3、hashMap高并发下除了写覆盖还会有什么问题？答案是循环引用
                }
            }
        }
    }
}