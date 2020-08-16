package com.sn.springboot.timedtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolService {
    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    // 参数代表可以同时执行的定时任务个数
    ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

    /**
     * schedule：延时2秒执行一次任务
     */
    public void task0() {
        service.schedule(() -> {
            logger.info(Thread.currentThread().getName() + "【task0】");
        }, 2, TimeUnit.SECONDS);
    }

    /**
     * scheduleAtFixedRate：2秒后，每间隔4秒执行一次任务
     * 注意，如果任务的执行时间（例如6秒）大于间隔时间，则会等待任务执行结束后直接开始下次任务
     */
    public void task1() {
        service.scheduleAtFixedRate(() -> {
            sleep(2);
            logger.info(Thread.currentThread().getName() + "【task1】");
        }, 2, 4, TimeUnit.SECONDS);
    }

    /**
     * scheduleWithFixedDelay：2秒后，每次延时4秒执行一次任务
     * 注意，这里是等待上次任务执行结束后，再延时固定时间后开始下次任务
     */
    public void task2() {
        service.scheduleWithFixedDelay(() -> {
            sleep(2);
            logger.info(Thread.currentThread().getName() + "【task2】");
        }, 2, 4, TimeUnit.SECONDS);
    }

    private void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ScheduledThreadPoolService scheduledThreadPoolService = new ScheduledThreadPoolService();
//        scheduledThreadPoolService.task0();
//        scheduledThreadPoolService.task1();
        scheduledThreadPoolService.task2();
    }
}
