package com.eht.evetrade.service.run;

import com.google.common.util.concurrent.Uninterruptibles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WorkerThread implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(WorkerThread.class);

    private Date lastRunTime = new Date();


    @Override
    public void run() {
        while (Active.getStatus()) {
            System.out.println(Thread.currentThread().getId() + ":" + lastRunTime);
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            lastRunTime = new Date();
            if (Thread.interrupted()) {
                System.out.println("interrupted");
                break;
            }
        }
    }
}
