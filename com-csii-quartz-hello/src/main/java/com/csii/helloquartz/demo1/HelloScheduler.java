package com.csii.helloquartz.demo1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.08.31 10:01
 */
public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("HelloScheduler:-->" + format.format(date));
        date.setTime(date.getTime()+3*1000);
        Date endDate = new Date();
        endDate.setTime(date.getTime()+6*1000);
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myjob")
                .usingJobData("message", "jobDetail")
                .usingJobData("floatValue", 0.22f)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .usingJobData("message", "triggerDetail")
                .usingJobData("doubleValue", 0.888D)
                .startAt(date)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

}
