package com.chikage.framework.quartzframework.controller;

import com.chikage.framework.quartzframework.common.BaseResponse;
import com.chikage.framework.quartzframework.manager.JobDetailManager;
import com.chikage.framework.quartzframework.model.JobCaller;
import com.chikage.framework.quartzframework.model.QuartzJobDetails;
import com.chikage.framework.quartzframework.manager.QuartzManager;
import com.chikage.framework.quartzframework.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: QuartzController
 * @package: com.chikage.framework.quartzframework.controller
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 3:43 PM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@RequestMapping(value = "/quartz")
public class QuartzController {
    @Autowired
    private QuartzService quartzService;
    @Autowired
    private QuartzManager quartzManager;
    @Autowired
    private JobDetailManager jobDetailManager;

    @PostMapping(value = "/add")
    public BaseResponse addQuartzJobDetails(@RequestBody QuartzJobDetails quartzJobDetails) throws Exception {
        return quartzManager.createQrtzJobDetails(quartzJobDetails);
    }

    //新增定时任务,记录job_caller参数
    @PostMapping(value = "/addJob")
    public BaseResponse addJob(@RequestBody JobCaller jobCaller) throws Exception{
        return quartzService.addJob(jobCaller);
    }

    @PostMapping(value = "/delete")
    public BaseResponse deleteQuartzJobDetails(@RequestBody QuartzJobDetails quartzJobDetails) throws Exception{
        return quartzManager.deleteQuartzJobDetails(quartzJobDetails);
    }

    //删除定时任务,记录job_caller参数
    @PostMapping(value = "/deleteJob")
    public BaseResponse deleteJob(@RequestBody JobCaller jobCaller) throws Exception{
        return quartzService.deleteJob(jobCaller);
    }
}
