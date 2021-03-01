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
        TwoPhaseTermination termination = new TwoPhaseTermination() ;
        log.debug("===启动系统中===");
        termination.start();
        log.debug("===启动系统完毕===");
        Thread.sleep(3000);
        termination.stop();
        System.out.println(Thread.State.BLOCKED);
    }
}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination{

    private Thread monitor ;

    /**
     * start monitor
     */
    public void start(){
        //thread run方法重写，使用函数式表达式
        monitor = new Thread(()->{
            while(true){
                Thread current = Thread.currentThread();
                if(current.isInterrupted()){

                    log.debug("关闭系统前的处理");
                    log.debug("系统安全退出、、、");
                    break ;
                }
                try {
                    //睡眠
                    Thread.sleep(500);
                    //记录监控信息
                    log.debug("执行监控任务、、、");
                } catch (InterruptedException e) {
                    log.debug("被打断了、、、");
                    e.printStackTrace();
                    //双重标记
                    current.interrupt();
                }
            }
        },"monitor") ;
        monitor.start();
    }

    /**
     * over monitor
     */
    public void stop(){
        monitor.interrupt();
//        monitor.resume();
//        monitor.suspend();
//        monitor.stop();
    }

}