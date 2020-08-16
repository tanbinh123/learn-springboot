package com.sn.springboot.timedtask.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuartzJob {
    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    public void hello(){
        logger.info("hello world");
    }
}
