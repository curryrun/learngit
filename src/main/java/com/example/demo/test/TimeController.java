package com.example.demo.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.MyJob.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by zdr on 2017/8/7.
 */
@Controller
@RequestMapping("/time")
public class TimeController {

    private final static String GROUPNAME = "MyJob";

    private static Long testId = new Long(1);

    @RequestMapping("/runone")
    @ResponseBody
    public JSONObject runone(@RequestParam(required = true) Long id) {
        System.out.println("logalert_runone start");

        executeOneQuartzJob(id, "my_test_name", "env");

        System.out.println("logalert_runone end");
        JSONObject res = new JSONObject();
        res.put("code", 1);
        return res;
    }

    @RequestMapping("/removeone")
    @ResponseBody
    public JSONObject removeone(@RequestParam(required = true) Long id) {
        System.out.println("logalert_removeone start");

        removeQuartzJob(id);

        System.out.println("logalert_removeone end");
        JSONObject res = new JSONObject();
        res.put("code", 1);
        return res;
    }

//    @RequestMapping("/getJob")
//    @ResponseBody
//    public JSONObject getJob() throws SchedulerException{
//        System.out.println("logalert_getJob start");
//        JSONObject res = new JSONObject();
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        List<String> jobList =  scheduler.get
//        if(null != jobList && 0 != jobList.size()){
//            res.put("list", jobList);
//            return res;
//        }
//        System.out.println("logalert_getJob end");
//        return res;
//    }

    @PostConstruct
    public void init() throws SchedulerException {
        System.out.println("logalert_init start");
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        addQuartzJob(testId, "localSystemName", "env", "0/5 * * * * ?");
        scheduler.start();
        System.out.println("logalert_init end");
    }

    private static void executeOneQuartzJob(Long id, String localSystemName, String env) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    .withIdentity(id+"",GROUPNAME).usingJobData("id", id).build();
            SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                    .withIdentity(id+"",GROUPNAME).startAt(new Date()).build();
//            scheduler.addJob(jobDetail, true);
            scheduler.scheduleJob(jobDetail, simpleTrigger);
//            scheduler.start();
//            Thread.sleep(5000);
//
//            TriggerKey triggerKey = TriggerKey.triggerKey(localSystemName + "_" + env + "_" + id, GROUPNAME);
//
//            scheduler.pauseTrigger(triggerKey);// 停止触发器
//            scheduler.unscheduleJob(triggerKey);
//            JobKey jobKey = JobKey.jobKey(localSystemName + "_" + env + "_" + id, GROUPNAME);
//            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void flush() throws SchedulerException {
        System.out.println("flush start");
        if(testId < 5){
            testId++;
            addQuartzJob(testId, "localSystemName", "env", "0/5 * * * * ?");
        }
        System.out.println("flush end:"+testId);
    }

    private static void addQuartzJob(Long id, String localSystemName, String env, String corntabEx) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    .withIdentity(id+"",GROUPNAME).usingJobData("id", id).build();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(id+"",GROUPNAME)
                    .withSchedule(CronScheduleBuilder.cronSchedule(corntabEx)).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void removeQuartzJob(Long id) {
        try {
            String jobName = id+"";
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, GROUPNAME);
//            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);
            JobKey jobKey = JobKey.jobKey(jobName, GROUPNAME);
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
