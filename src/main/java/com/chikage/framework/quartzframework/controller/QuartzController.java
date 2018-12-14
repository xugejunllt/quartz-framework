package com.chikage.framework.quartzframework.controller;

import com.chikage.framework.quartzframework.model.QuartzJobDetails;
import com.chikage.framework.quartzframework.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping(value = "/add")
    public Map<String, Object> addQuartzJobDetails(@RequestBody QuartzJobDetails quartzJobDetails) throws Exception {
        Map<String, Object> map = new HashMap<>();
        quartzService.createQrtzJobDetails(quartzJobDetails);
        map.put("success", true);
        map.put("msg", "定时任务添加成功");
        return map;
    }

    @PostMapping(value = "/delete")
    public Map<String,Object> deleteQuartzJobDetails(@RequestBody QuartzJobDetails quartzJobDetails) throws Exception{
        Map<String, Object> map = new HashMap<>();
        quartzService.deleteQuartzJobDetails(quartzJobDetails);
        map.put("success", true);
        map.put("msg", "定时任务删除成功");
        return map;
    }
}
