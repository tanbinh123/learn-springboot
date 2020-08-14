package com.sn.springboot.timedtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ScheduleService {
    Logger logger = LoggerFactory.getLogger("ScheduleService");

    /**
     * fixedRate：任务开始的时间间隔，第二次开始时第一次可能还没结束
     */
//    @Scheduled(fixedRate = 5000)
    public void task0() {
        sleep(5);
        logger.info(Thread.currentThread().getName() + "【fixedRate】");
    }

    /**
     * fixedDelay：本次任务结束到下次任务开始的时间间隔
     */
//    @Scheduled(fixedDelay = 5000)
    public void task1() {
        sleep(5);
        logger.info(Thread.currentThread().getName() + "【fixedDelay】");
    }

    /**
     * fixedDelay：首次任务启动的延时时间
     */
//    @Scheduled(initialDelay = 2000, fixedDelay = 3000)
    public void task2() {
        logger.info(Thread.currentThread().getName() + "【initialDelay】");
    }

    /**
     * 使用表达式配置
     * 1、按顺序依次是：秒、分、时、日、月、周、年，年可以不配置
     * 2、日和星期可能会冲突，因此两个需要有一个配置为?
     * 3、
     * *表示任意值，例如在秒字段上设置*，表示每秒都触发
     * ?表示不指定值，用于处理日和星期的冲突
     * -表示时间区间，例如在秒上设置1-3，表示第1、2、3秒都会触发
     * /表示时间间隔，例如在秒上设置2/4，表示从第2秒开始每间隔4秒触发一次
     * ,表示列举多个值，例如MON,WED,FRI表示周一、周三、周五触发
     * #表示每月的第几周的周几，例如5#2用在周上表示每月第二周的周五
     *
     *  Spring 的表达式 只是 cron表达式的子集，它不包含year字段，并且不能使用所有特殊字符，比如L和W，大部分的文档有误导。
     */
    @Scheduled(cron = "0 0 18 * * FRI")
    public void task3() {
        logger.info(Thread.currentThread().getName() + "【cron】");
    }

    private void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
