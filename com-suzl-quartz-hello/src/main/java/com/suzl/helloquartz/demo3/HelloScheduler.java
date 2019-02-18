package com.suzl.helloquartz.demo3;

import com.suzl.helloquartz.demo2.HelloJob;
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

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                .build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

}
