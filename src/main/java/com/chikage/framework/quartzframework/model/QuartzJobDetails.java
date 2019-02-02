package com.chikage.framework.quartzframework.model;

import lombok.Data;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: QrtzJobDetails
 * @package: com.chikage.framework.quartzframework.model
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 2:49 PM
 * @version: v1.0
 */
@Data
public class QuartzJobDetails {
    private String schedName;
    private String jobName;
    private String jobGroup;
    private String description;
    private String jobClassName;
    private String isDurable;
    private String isNonconcurrent;
    private String isUpdateData;
    private String requestsRecovery;
    private String jobData;
    private String cronExpression;
}
