package com.chikage.framework.quartzframework.manager;

import com.chikage.framework.quartzframework.log.ErrorLog;
import com.chikage.framework.quartzframework.log.LogTreadLocal;
import com.chikage.framework.quartzframework.model.JobScheduleLog;
import com.chikage.framework.quartzframework.repository.JobScheduleLogMapper;
import com.chikage.framework.quartzframework.utils.ApplicationContextWare;
import com.chikage.framework.quartzframework.utils.date.DateTimeUtil;
import org.quartz.JobExecutionContext;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.chikage.framework.quartzframework.utils.date.DateStyle.YYYY_MM_DD_HH_MM_SS;
import static com.chikage.framework.quartzframework.utils.date.DateStyle.YYYY_MM_DD_HH_MM_SS_TT;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: JobScheduleLogManager
 * @package: com.chikage.framework.quartzframework.manager
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/17 11:21 AM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component
public class JobScheduleLogManager {
    @Autowired
    private JobScheduleLogMapper logMapper;

    public int trans2JobLogBefore(JobExecutionContext jobContext) {
        try {
            JobScheduleLog log = new JobScheduleLog();
            log.setJobFiretime(DateTimeUtil.DateToString(jobContext.getFireTime(), YYYY_MM_DD_HH_MM_SS_TT));
            log.setJobName(((JobDetailImpl) jobContext.getJobDetail()).getName());
            log.setJobInstanceId(jobContext.getFireInstanceId());
            int n = logMapper.insert(log);
//            System.out.println(log.getId());

            return n > 0 ? log.getId() : -1;

        } catch (Exception ex) {
            ErrorLog.errorConvertJson(ApplicationContextWare.getAppName(), LogTreadLocal.getTrackingNo(), this.getClass(), "sql:新增日志异常", ex);
            return -2;
        }
    }

    public void trans2JobLogAfter(JobExecutionContext jobContext,int id) {
        try {
            JobScheduleLog log = new JobScheduleLog();
            log.setJobEndtime(DateTimeUtil.timestampToString(String.valueOf(System.currentTimeMillis()), YYYY_MM_DD_HH_MM_SS_TT.getValue()));
//        log.setJobRuntime(String.valueOf(jobContext.getJobRunTime()));
            log.setId(id);
            jobContext.put("id",id);
            logMapper.updateByPrimaryKeySelective(log);
        } catch (Exception ex) {
            ErrorLog.errorConvertJson(ApplicationContextWare.getAppName(), LogTreadLocal.getTrackingNo(), this.getClass(), "sql:更新日志异常", ex);
        }
    }
}
