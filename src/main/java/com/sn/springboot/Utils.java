package com.sn.springboot;

import java.util.concurrent.TimeUnit;

public class Utils {
    public static void sleep(TimeUnit timeUnit, long time) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
