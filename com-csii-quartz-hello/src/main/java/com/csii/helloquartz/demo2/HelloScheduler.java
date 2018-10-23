package com.csii.helloquartz.demo2;

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
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myjob")
                .build();
        date.setTime(date.getTime() + 4 * 1000);
        //1. 4s之后开始执行且执行1次
        // SimpleTrigger trigger = TriggerBuilder.newTrigger()
        //         .startAt(date)
        //         .build();
        // 2. 4s之后开始执行 之后每隔2s执行一次
        // SimpleTrigger trigger = TriggerBuilder.newTrigger()
        //         .startAt(date)
        //         .withSchedule(SimpleScheduleBuilder.simpleSchedule()
        //                 .withIntervalInSeconds(2).
        //                         withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY))
        //         .build();
        //3. 4s之后开始执行,之后每隔2s执行一次 第一次之后重复执行3次
        // SimpleTrigger trigger = TriggerBuilder.newTrigger()
        //         .startAt(date)
        //         .withSchedule(SimpleScheduleBuilder.simpleSchedule()
        //                 .withIntervalInSeconds(2).
        //                         withRepeatCount(3))
        //         .build();
        //4. 4s之后开始执行,之后每隔2s执行一次 6s之后停止
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 6 * 1000);
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .startAt(date)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).
                                withRepeatCount(3))
                .build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

}
