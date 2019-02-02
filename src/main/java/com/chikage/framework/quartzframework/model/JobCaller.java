package com.chikage.framework.quartzframework.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: QuartzCaller
 * @package: com.chikage.framework.quartzframework.model
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/16 5:50 PM
 * @version: v1.0
 */
@Data
public class JobCaller {
    @NotNull
    private String jobName;
    private String jobGroup;
    private String jobClass;
    private String jobData;
    private String jobDescription;
    @NotNull
    private String cronExpression;
}