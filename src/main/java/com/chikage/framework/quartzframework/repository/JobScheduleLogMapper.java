package com.chikage.framework.quartzframework.repository;

import com.chikage.framework.quartzframework.model.JobScheduleLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: JobScheduleLogMapper
 * @package: com.chikage.framework.quartzframework.repository
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/17 10:58 AM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Mapper
public interface JobScheduleLogMapper {
    //增加记录
    int insert(JobScheduleLog jobScheduleLog);
    //更新记录
    int updateByPrimaryKeySelective(JobScheduleLog jobScheduleLog);


}
