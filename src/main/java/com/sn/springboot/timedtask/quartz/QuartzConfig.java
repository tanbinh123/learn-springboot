package com.sn.springboot.timedtask.quartz;

import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

//@Configuration
public class QuartzConfig {
    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        // 指定任务类在IoC容器中的Bean名称
        bean.setTargetBeanName("quartzJob");
        // 指定要执行的方法名称
        bean.setTargetMethod("hello");
        return bean;
    }

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        // 指定任务类名称
        bean.setJobClass(QuartzJob2.class);
        // 准备参数
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("date", "2020-8-16");
        // 传递参数
        bean.setJobDataMap(jobDataMap);
        return bean;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setRepeatCount(10);
        bean.setRepeatInterval(2000);
        bean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/2 * * * * ? 2020");
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        return bean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(simpleTriggerFactoryBean().getObject(), cronTriggerFactoryBean().getObject());
        return bean;
    }
}
