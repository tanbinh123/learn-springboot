package com.sn.springboot.timedtask.quartz;

import com.sn.springboot.Utils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.TimeUnit;

public class QuartzJob2 extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("job1-start");
        Utils.sleep(TimeUnit.SECONDS, 4);
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String date = jobDataMap.getString("date");
        logger.info("参数：" + date);
        logger.info("job1-end");
    }
}
