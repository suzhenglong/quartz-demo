package com.csii.helloquartz.demo1;

import org.quartz.*;

import javax.sound.midi.Soundbank;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @author: zhenglongsu@163.com
 * @date: 2018.08.31 09:13
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("HelloJob:-->" + format.format(new Date()));
        System.out.println("hello world");
        //1.
        JobKey jobKey = context.getJobDetail().getKey();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        System.out.println("jobKey:-->" + jobKey.getName() + "--" + jobKey.getGroup());
        System.out.println("jobDataMap-->message:" + jobDataMap.getString("message"));
        System.out.println("jobDataMap-->floatValue:" + jobDataMap.getFloat("floatValue"));

        TriggerKey triggerKey = context.getTrigger().getKey();
        JobDataMap dataMap = context.getTrigger().getJobDataMap();
        System.out.println("triggerKey:-->" + triggerKey.getName() + "--" + triggerKey.getGroup());
        System.out.println("dataMap-->message:" + dataMap.getString("message"));
        System.out.println("dataMap-->doubleValue:" + dataMap.getDouble("doubleValue"));
        System.out.println("=================================================================");
        //2.
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        System.out.println("mergedJobDataMap-->message:" + mergedJobDataMap.get("message"));
        System.out.println("=================================================================");
        //3.
        System.out.println("message:" + message);
        System.out.println("floatValue:" + floatValue);
        System.out.println("doubleValue:" + doubleValue);
        System.out.println("=================================================================");
        //4.
        Date startTime = context.getTrigger().getStartTime();
        Date endTime = context.getTrigger().getEndTime();
        System.out.println("startTime:" + format.format(startTime));
        System.out.println("endTime" + format.format(endTime));
        System.out.println("=================================================================");
    }

    private String message;
    private float floatValue;
    private double doubleValue;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }
}
