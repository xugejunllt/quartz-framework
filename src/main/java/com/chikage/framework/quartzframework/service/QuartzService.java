package com.chikage.framework.quartzframework.service;

import com.chikage.framework.quartzframework.model.QuartzJobDetails;
import com.chikage.framework.quartzframework.quartz.cluster.job.DynamicQuartzJob;
import com.chikage.framework.quartzframework.repository.QuartzJobDetailsMapper;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: QrtzJobDetailsService
 * @package: com.chikage.framework.quartzframework.service
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 3:37 PM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component
public class QuartzService {
    /** jobName 前缀*/
    private static final String JOB_NAME_PREFIX = "jobName.";
    /** triggerName 前缀*/
    private static final String TRIGGER_NAME_PREFIX = "triggerName.";
    /** jobName/triggerName 默认组 */
    private static final String GROUP_DEFAULT = "DEFAULT";

    @Autowired
    private QuartzJobDetailsMapper quartzJobDetailsMapper;

    @Autowired
    private Scheduler scheduler;

    public void createQrtzJobDetails(QuartzJobDetails quartzJobDetails) throws Exception{

        // 非空校验
        if (quartzJobDetails == null) {
            throw new Exception("qrtzJobDetails 为空");
        }
        if (StringUtils.isBlank(quartzJobDetails.getJobName())) {
            throw new Exception("qrtzJobDetails serviceInfo 为空");
        }

//        // 定时服务有效性校验 (校验是否存在对应的servcie.method )
//        this.checkServiceAndMethod(quartzJobDetails.getJobName());

        // 唯一性校验
        String jobName = JOB_NAME_PREFIX + quartzJobDetails.getJobName();
        String triggerName = TRIGGER_NAME_PREFIX + quartzJobDetails.getJobName();
        String jobGroup = StringUtils.isBlank(quartzJobDetails.getJobGroup())? GROUP_DEFAULT : quartzJobDetails.getJobGroup();
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
//        if (scheduler.checkExists(jobKey)) {
//            throw new DynamicQuartzException(quartzJobDetails.getJobName() + "服务方法对应定时任务已经存在!");
//        }

        // 构建job信息
        JobDetail job = JobBuilder.newJob(DynamicQuartzJob.class).withIdentity(jobKey).withDescription(quartzJobDetails.getDescription()).build();
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, jobGroup);

        // 构建job的触发规则 cronExpression
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(quartzJobDetails.getCronExpression())).build();

        // 注册job和trigger信息
        scheduler.scheduleJob(job, trigger);

    }
}
