package com.example.demo.myjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAlertJob implements Job {
    Logger logger = LoggerFactory.getLogger(LogAlertJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("================run!!!");
        Long id = (Long) context.getMergedJobDataMap().get("id");
    }

}
