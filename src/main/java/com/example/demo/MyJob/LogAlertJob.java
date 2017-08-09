package com.example.demo.MyJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAlertJob implements Job {
    Logger logger = LoggerFactory.getLogger(LogAlertJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("================run!!!");
        Long id = (Long) context.getMergedJobDataMap().get("id");
    }

}
