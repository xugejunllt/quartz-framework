package com.chikage.framework.quartzframework.quartz.cluster.jobExecuter;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
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
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class DynamicQuartzJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
        JobDetailImpl jobDetail = (JobDetailImpl) jobContext.getJobDetail();
        String name = jobDetail.getName();
        if (StringUtils.isEmpty(name)) {
            throw new JobExecutionException("can not find service info, because desription is empty");
        }
        String[] serviceInfo = StringUtils.delimitedListToStringArray(name, ".");
        // serviceInfo[0] is JOB_NAME_PREFIX
        String beanName = serviceInfo[1];
        String methodName = serviceInfo[2];
        Object serviceImpl = getApplicationContext(jobContext).getBean(beanName);
        Method method;
        try {
            Class<?>[] parameterTypes = new Class[]{String.class};
            Object[] arguments = null;
            method = serviceImpl.getClass().getMethod(methodName,parameterTypes);
//            logger.info("dynamic invoke {}.{}()", serviceImpl.getClass().getName(), methodName);
//			method.invoke(serviceImpl, arguments);
            method.invoke(serviceImpl, jobContext.getJobDetail().getJobDataMap().getString("jobData"));
        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
//            logger.error("reflect invoke service method error", e);
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