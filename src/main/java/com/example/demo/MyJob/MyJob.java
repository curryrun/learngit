package com.example.demo.MyJob;

import com.example.demo.service.MyService;
import com.example.demo.util.SpringBeanFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by finup on 2017/8/7.
 */
public class MyJob implements Job {

//    @Autowired
//    private MyService myService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Long id = (Long) context.getMergedJobDataMap().get("id");
        System.out.println("MyJob id is "+id);
//        String back = myService.getFromService(id);
        MyService myService = SpringBeanFactory.getBean(MyService.class);
        String back = myService.getFromService(id);
        System.out.println("from Service:"+back);
    }
}
