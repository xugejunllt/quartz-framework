package com.chikage.framework.quartzframework.controller;

import com.chikage.framework.quartzframework.model.QuartzJobDetails;
import com.chikage.framework.quartzframework.repository.QuartzJobDetailsMapper;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: HelloController
 * @package: com.chikage.framework.quartzframework.controller
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 2:58 PM
 * @version: v1.0
 */
@RestController
public class HelloController {
    @Autowired
    QuartzJobDetailsMapper quartzJobDetailsMapper;

    @PostMapping("/hello")
    public String hello(@RequestBody QuartzJobDetails quartzJobDetails) throws SQLException {
//        quartzJobDetailsMapper.insert(quartzJobDetails);
        List<Map<String, Object>> maps = quartzJobDetailsMapper.selectMapList(quartzJobDetails);
//        QuartzJobDetails jobDetails = quartzJobDetailsMapper.selectByPrimaryKey("schedulerFactoryBean");
//        JobDataMap jobDataMap =
//        Blob b = new SerialBlob(jobDetails.getJobData().getBytes());
//        String blobString = new String(b.getBytes(1, (int) b.length()));//blob 转 String



//        String s = new String(jobDetails.getJobData().getBytes(1, (int) jobDetails.getJobData().length(),"GBK"));
        return "/index";
    }
}
