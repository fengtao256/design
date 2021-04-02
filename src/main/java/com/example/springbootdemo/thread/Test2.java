package com.example.springbootdemo.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test2.java
 * @Description 烧水泡茶
 * @createTime 2021年01月25日 17:05:00
 */
@Slf4j
public class Test2 {
    /**
     * 如何实现谁先完成谁来泡茶呢？
     *
     * @param args
     */
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                log.info("小明-洗水壶");
                Thread.sleep(1000);
                log.info("小明-烧开水");
                Thread.sleep(5000);
                log.info("小明-烧开水完毕");
                log.info("小明-完成工作");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "小明");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                log.info("小李-洗茶壶");
                Thread.sleep(1000);
                log.info("小李-洗茶杯");
                Thread.sleep(1000);
                log.info("小李-拿茶叶");
                Thread.sleep(1000);

                log.info("小李-完成工作,等待中");
                //等待小明工作完成后泡茶
                thread1.join();
                log.debug("小李-泡茶");
                Thread.sleep(1000);
                log.debug("小李-完成泡茶");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "小李");
        thread2.start();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ExecutorService service = new ThreadPoolExecutor(2,
                10,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                threadFactory);
        Future<String> futureTask = service.submit(new FutureTaskA()) ;
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
        service.shutdownNow() ;
//        service;
    }

    static class FutureTaskA implements Callable{

        @Override
        public Object call() throws Exception {
            int index =0 ;
            while(true) {
                System.out.println("running");
                TimeUnit.SECONDS.sleep(1L);
                if (index++ == 10) {
                    break;
                }
            }
            return "hei !";
        }
    }
}
