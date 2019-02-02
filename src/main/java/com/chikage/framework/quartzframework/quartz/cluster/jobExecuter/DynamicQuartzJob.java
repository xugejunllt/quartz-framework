package com.chikage.framework.quartzframework.quartz.cluster.jobExecuter;

import com.chikage.framework.quartzframework.log.ErrorLog;
import com.chikage.framework.quartzframework.log.LogTreadLocal;
import com.chikage.framework.quartzframework.manager.JobScheduleLogManager;
import com.chikage.framework.quartzframework.quartz.cluster.quartzListener.MyJobListener;
import com.chikage.framework.quartzframework.quartz.cluster.quartzListener.MyTriggerListener;
import com.chikage.framework.quartzframework.utils.ApplicationContextWare;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: DynamicQuartzJob
 * @package: com.chikage.framework.quartzframework.quartz.cluster.job
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 3:33 PM
 * @version: v1.0
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class DynamicQuartzJob extends QuartzJobBean {
    @Autowired
    private JobScheduleLogManager jobManager;

    @Override
    protected void executeInternal(JobExecutionContext jobContext) {
        try {
            int i = jobManager.trans2JobLogBefore(jobContext);
            if (i <= 0) return;
            JobDetailImpl jobDetail = (JobDetailImpl) jobContext.getJobDetail();
            String name = jobDetail.getName();
            if (StringUtils.isEmpty(name)) {
                throw new JobExecutionException("can not find service info, because desription is empty");
            }
            //注册job和trigger的监听器
            JobKey jobKey = jobContext.getJobDetail().getKey();
            TriggerKey triggerKey = jobContext.getTrigger().getKey();
            jobContext.getScheduler().getListenerManager().addJobListener(new MyJobListener(), KeyMatcher.keyEquals(jobKey));
            jobContext.getScheduler().getListenerManager().addTriggerListener(new MyTriggerListener(), KeyMatcher.keyEquals(triggerKey));

            String[] serviceInfo = StringUtils.delimitedListToStringArray(name, ".");
            // serviceInfo[0] is JOB_NAME_PREFIX
            String beanName = serviceInfo[1];
            String methodName = serviceInfo[2];
            Object serviceImpl = getApplicationContext(jobContext).getBean(beanName);
            Method method;
//        try {
            Class<?>[] parameterTypes = new Class[]{String.class};
            Object[] arguments = null;
            method = serviceImpl.getClass().getMethod(methodName, parameterTypes);
//            logger.info("dynamic invoke {}.{}()", serviceImpl.getClass().getName(), methodName);
//			method.invoke(serviceImpl, arguments);
            method.invoke(serviceImpl, jobContext.getJobDetail().getJobDataMap().getString("jobData"));
            jobManager.trans2JobLogAfter(jobContext, i);
        } catch (Exception ex) {
            ErrorLog.errorConvertJson(ApplicationContextWare.getAppName(), LogTreadLocal.getTrackingNo(), this.getClass(), "quartz定时任务execute异常", ex);

//            logger.error("reflect  invoke service method error", e);
        }
    }

    private ApplicationContext getApplicationContext(final JobExecutionContext jobexecutioncontext) {
        try {
            //applicationContext 在SchedulerFactoryBean中配置
            return (ApplicationContext) jobexecutioncontext.getScheduler().getContext().get("applicationContext");
        } catch (SchedulerException e) {
//            logger.error("jobexecutioncontext.getScheduler().getContext() error!", e);
            throw new RuntimeException(e);
        }
    }
}
