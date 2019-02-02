package com.chikage.framework.quartzframework.quartz.cluster.quartzListener;

import com.chikage.framework.quartzframework.model.JobScheduleLog;
import com.chikage.framework.quartzframework.repository.JobScheduleLogMapper;
import com.chikage.framework.quartzframework.utils.SpringContextHolder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import java.util.Optional;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: SchedulerListener
 * @package: com.chikage.framework.quartzframework.quartz.cluster.jobListener
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 6:48 PM
 * @version: v1.0
 */
public class MyJobListener implements JobListener {
    public static final String LISTENER_NAME = "MyJobListener";

//    @Autowired
//    private JobScheduleLogMapper logMapper;

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
        //todo:原因捕获

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
        JobScheduleLog log = new JobScheduleLog();
        log.setJobRuntime(String.valueOf(context.getJobRunTime()));
        log.setId(Optional.ofNullable(context.get("id")).map(p->Integer.parseInt(String.valueOf(context.get("id")))).orElse(null));
        JobScheduleLogMapper logMapper = SpringContextHolder.getBean(JobScheduleLogMapper.class);
        logMapper.updateByPrimaryKeySelective(log);
    }
}
