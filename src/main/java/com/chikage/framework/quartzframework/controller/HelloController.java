package com.chikage.framework.quartzframework.controller;

import com.chikage.framework.quartzframework.model.QuartzJobDetails;
import com.chikage.framework.quartzframework.repository.QuartzJobDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: HelloController
 * @package: com.chikage.framework.quartzframework.controller
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 2:58 PM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
public class HelloController {
    @Autowired
    QuartzJobDetailsMapper quartzJobDetailsMapper;

    @PostMapping("/hello")
    public String hello(@RequestBody QuartzJobDetails quartzJobDetails){
        quartzJobDetailsMapper.insert(quartzJobDetails);
        return "/index";
    }
}
