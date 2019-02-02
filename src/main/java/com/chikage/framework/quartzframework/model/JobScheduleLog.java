package com.chikage.framework.quartzframework.model;

import lombok.Data;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: JobScheduleLog
 * @package: com.chikage.framework.quartzframework.model
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/17 10:56 AM
 * @version: v1.0
 */
@Data
public class JobScheduleLog {
    private Integer id;
    private String jobName;
    private String jobInstanceId;
    private String jobFiretime;
    private String jobEndtime;
    private String jobRuntime;
}
