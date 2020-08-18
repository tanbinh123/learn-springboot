package com.sn.springboot.timedtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ScheduleService {
    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    /**
     * fixedRate：每间隔2秒执行一次任务
     * 注意，默认情况下定时任务是在同一线程同步执行的，如果任务的执行时间（例如6秒）大于间隔时间，则会等待任务执行结束后直接开始下次任务
     */
//    @Scheduled(fixedRate = 2000)
    public void task0() {
        logger.info("task0-start");
        sleep(6);
        logger.info("task0-end");
    }

    /**
     * fixedDelay：每次延时2秒执行一次任务
     * 注意，这里是等待上次任务执行结束后，再延时固定时间后开始下次任务
     */
//    @Scheduled(fixedDelay = 2000)
    public void task1() {
        logger.info("task1-start");
        sleep(6);
        logger.info("task1-end");
    }

    /**
     * initialDelay：首次任务启动的延时时间
     */
//    @Scheduled(initialDelay = 2000, fixedDelay = 3000)
    public void task2() {
        logger.info("task2-start");
        sleep(6);
        logger.info("task2-end");
    }

    /**
     * 使用表达式配置
     * 1、按顺序依次是：秒、分、时、日、月、周
     * 2、日和星期可能会冲突，因此两个需要有一个配置为?
     * 3、
     * *表示任意值，例如在秒字段上设置*，表示每秒都触发
     * ?表示不指定值，用于处理日和星期的冲突
     * -表示时间区间，例如在秒上设置1-3，表示第1、2、3秒都会触发
     * /表示时间间隔，例如在秒上设置2/4，表示从第2秒开始每间隔4秒触发一次
     * ,表示列举多个值，例如MON,WED,FRI表示周一、周三、周五触发
     */
//    @Scheduled(cron = "0 59 23 * * ?")
    public void task3() {
        logger.info("task3-start");
        sleep(6);
        logger.info("task3-end");
    }

    private void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
