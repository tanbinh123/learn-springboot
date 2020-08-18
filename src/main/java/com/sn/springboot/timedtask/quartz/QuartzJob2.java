package com.sn.springboot.timedtask.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzJob2 extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String date = jobDataMap.getString("date");
        logger.info("hello world " + date);
    }
}
