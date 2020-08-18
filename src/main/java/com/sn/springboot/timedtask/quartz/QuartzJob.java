package com.sn.springboot.timedtask.quartz;

import com.sn.springboot.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class QuartzJob {
    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    public void hello() {
        logger.info("job0-start");
        Utils.sleep(TimeUnit.SECONDS, 4);
        logger.info("job0-end");
    }
}
