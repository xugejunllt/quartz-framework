package com.chikage.framework.quartzframework.service;

import com.chikage.framework.quartzframework.common.BaseResponse;
import com.chikage.framework.quartzframework.log.ErrorLog;
import com.chikage.framework.quartzframework.log.LogTreadLocal;
import com.chikage.framework.quartzframework.manager.JobDetailManager;
import com.chikage.framework.quartzframework.manager.QuartzManager;
import com.chikage.framework.quartzframework.model.JobCaller;
import com.chikage.framework.quartzframework.model.QuartzJobDetails;
import com.chikage.framework.quartzframework.repository.JobCallerMapper;
import com.chikage.framework.quartzframework.utils.ApplicationContextWare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: QuartzService
 * @package: com.chikage.framework.quartzframework.service
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/16 8:53 PM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component
public class QuartzService {
    @Autowired
    private QuartzManager quartzManager;
    @Autowired
    private JobDetailManager jobDetailManager;
    @Autowired
    private JobCallerMapper jobCallerMapper;

    @Transactional
    public BaseResponse addJob(JobCaller jobCaller) {
        try {
            String jobGroup = jobCaller.getJobGroup() != null ? jobCaller.getJobGroup() : "DEFAULT";
            jobCaller.setJobGroup(jobGroup);
            jobCallerMapper.insert(jobCaller);
            QuartzJobDetails jobDetails = jobDetailManager.trans2QuartzJobDetail(jobCaller);
            return quartzManager.createQrtzJobDetails(jobDetails);
        } catch (Exception ex) {
            ErrorLog.errorConvertJson(ApplicationContextWare.getAppName(), LogTreadLocal.getTrackingNo(), this.getClass(), "service-新增quartz定时任务异常", ex);
        }
        return new BaseResponse(101, "失败", null);

    }

    @Transactional
    public BaseResponse deleteJob(JobCaller jobCaller) {
        try {
            jobCallerMapper.deleteByPrimaryKey(jobCaller.getJobName());
            QuartzJobDetails jobDetails = jobDetailManager.trans2QuartzJobDetail(jobCaller);
            return quartzManager.deleteQuartzJobDetails(jobDetails);
        } catch (Exception ex) {
            ErrorLog.errorConvertJson(ApplicationContextWare.getAppName(), LogTreadLocal.getTrackingNo(), this.getClass(), "service-删除quartz定时任务异常", ex);
        }
        return new BaseResponse(101, "失败", null);
    }
}
