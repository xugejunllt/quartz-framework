package com.chikage.framework.quartzframework.service;

import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: HelloService
 * @package: com.chikage.framework.quartzframework.service
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 2:58 PM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component
public class HelloService {

    public void sayHello(String a) {
        System.out.println(a+"======hello world, i am quartz");
    }

    public void callHello(String b) {
        System.out.println(b+"======call");
    }
}
