package com.csii.helloquartz.demo2;

import org.quartz.*;

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

        System.out.println("=================================================================");
    }
}
