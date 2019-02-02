package com.chikage.framework.quartzframework.repository;

import com.chikage.framework.quartzframework.model.QuartzJobDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: QuartzDetailsDao
 * @package: com.chikage.framework.quartzframework.repository
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 2:43 PM
 * @version: v1.0
 */
@Mapper
public interface QuartzJobDetailsMapper {
    //增加记录
    int insert(@Param("vo") QuartzJobDetails quartzJobDetails);
    //删除记录
    int deleteByPrimaryKey(String id);

    List<Map<String, Object>> selectMapList(QuartzJobDetails qrtzJobDetails);

    QuartzJobDetails selectByPrimaryKey(@Param("schedName") String schedName);
}
