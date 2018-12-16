package com.chikage.framework.quartzframework.model;

import lombok.Data;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: QuartzCaller
 * @package: com.chikage.framework.quartzframework.model
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/16 5:50 PM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class JobCaller {
    private String jobName;
    private String jobGroup;
    private String jobClass;
    private String jobData;
    private String jobDescription;
    private String cronExpression;
}