package com.chikage.framework.quartzframework.log;

import java.lang.annotation.*;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.freemud.mail.core
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: aiqi.gong
 * @date: 2018/6/20 9:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.PARAMETER})
@Documented
public @interface LogParams {
}
