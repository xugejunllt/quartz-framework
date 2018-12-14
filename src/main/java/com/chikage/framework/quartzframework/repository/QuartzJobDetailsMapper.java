package com.chikage.framework.quartzframework.repository;

import com.chikage.framework.quartzframework.model.QuartzJobDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: QuartzDetailsDao
 * @package: com.chikage.framework.quartzframework.repository
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 2:43 PM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Mapper
public interface QuartzJobDetailsMapper {
    //增加记录
    int insert(@Param("vo") QuartzJobDetails quartzJobDetails);
    //删除记录
    int deleteByPrimaryKey(String id);
}
