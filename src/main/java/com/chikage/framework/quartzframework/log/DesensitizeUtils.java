package com.chikage.framework.quartzframework.log;

import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: DesensitizeUtils
 * @package: cn.freemud.svc.adapter.base
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/10/30 11:36 AM
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component
public class DesensitizeUtils {

    public static String DesensitizeCardBin(String data) {
//        return data.replaceAll("(?<=90\\d{17})=[0-9a-zA-Z]{16}", "=****************");
        //16-19位卡号=16位卡密
        return data.replaceAll("(?<=\\d{16,19})=[0-9a-zA-Z]{16}", "=****************");
    }
}
