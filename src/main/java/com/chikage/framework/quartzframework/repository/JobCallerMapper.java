package com.chikage.framework.quartzframework.repository;

import com.chikage.framework.quartzframework.model.JobCaller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: JobCallerMapper
 * @package: com.chikage.framework.quartzframework.repository
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/16 6:05 PM
 * @version: v1.0
 */
@Mapper
public interface JobCallerMapper {
    JobCaller selectByPrimaryKey(@Param("jobName") String jobName, @Param("cronExpression") String cronExpression);
    //增加记录
    int insert(JobCaller jobCaller);
    //删除记录
    int deleteByPrimaryKey(@Param("jobName") String jobName,@Param("cronExpression")String cronExpression);

    //删除记录
    int delete(JobCaller jobCaller);
}
