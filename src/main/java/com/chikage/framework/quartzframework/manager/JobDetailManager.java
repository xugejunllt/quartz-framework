package com.chikage.framework.quartzframework.manager;

import com.chikage.framework.quartzframework.model.JobCaller;
import com.chikage.framework.quartzframework.model.QuartzJobDetails;
import com.chikage.framework.quartzframework.repository.JobCallerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: JobDetailManager
 * @package: com.chikage.framework.quartzframework.manager
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/16 6:10 PM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component
public class JobDetailManager {
    @Autowired
    private JobCallerMapper jobCallerMapper;

    public QuartzJobDetails trans2QuartzJobDetail(JobCaller jobCaller) {
//        jobCallerMapper.insert(jobCaller);
        QuartzJobDetails jobDetails = new QuartzJobDetails();
        jobDetails.setJobName(jobCaller.getJobName());
        jobDetails.setJobGroup(jobCaller.getJobGroup());
        jobDetails.setDescription(jobCaller.getJobDescription());
        jobDetails.setJobData(jobCaller.getJobData());
        jobDetails.setJobClassName(jobCaller.getJobClass());
        jobDetails.setCronExpression(jobCaller.getCronExpression());
        return jobDetails;
    }
}
