package com.chikage.framework.quartzframework.quartz.cluster.quartzListener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: SchedulerListener
 * @package: com.chikage.framework.quartzframework.quartz.cluster.jobListener
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 6:48 PM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
public class MyJobListener implements JobListener {
    public static final String LISTENER_NAME = "QuartzSchedulerListener";

    @Override
    public String getName() {
        return LISTENER_NAME; //must return a name
    }

    //任务被调度前
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

        String jobName = context.getJobDetail().getKey().toString();
//        System.out.println("jobToBeExecuted");
        System.out.println("Job调度前 : " + jobName + " is going to start...");

    }

    //任务调度被拒了
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("Job调度被拒:jobExecutionVetoed");
        //可以做一些日志记录原因

    }

    //任务被调度后
    @Override
    public void jobWasExecuted(JobExecutionContext context,
                               JobExecutionException jobException) {
//        System.out.println("Job调度器:jobWasExecuted");

        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("Job调度后 : " + jobName + " is finished...");

        if (jobException!=null&&!jobException.getMessage().equals("")) {
            System.out.println("Exception thrown by: " + jobName
                    + " Exception: " + jobException.getMessage());
        }

    }
}
