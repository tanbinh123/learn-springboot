package com.sn.springboot.timedtask.quartz;

import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

@Configuration
public class QuartzConfig {
//    @Bean
//    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
//        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
//        bean.setTargetBeanName("quartzJob");
//        bean.setTargetMethod("hello");
//        return bean;
//    }

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(QuartzJob2.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("date", "2020-8-16");
        bean.setJobDataMap(jobDataMap);
        return bean;
    }

//    @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
//        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
//        bean.setRepeatCount(10);
//        bean.setRepeatInterval(2000);
//        bean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
//        return bean;
//    }

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
        bean.setTriggers(cronTriggerFactoryBean().getObject());
        return bean;
    }
}
