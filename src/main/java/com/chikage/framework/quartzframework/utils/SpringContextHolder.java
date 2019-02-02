package com.chikage.framework.quartzframework.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: SpringContextHolder
 * @package: com.chikage.framework.quartzframework.utils
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/14 5:09 PM
 * @version: v1.0
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    private Log log = LogFactory.getLog(getClass());

    /**
     *
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextHolder.applicationContext=applicationContext;
        log.debug("ApplicationContext registed");
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 校验Bean是否存在方法
     * @param beanName
     * @param methodName
     * @param parameterTypes
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public static boolean existBeanAndMethod(String beanName, String methodName, Class<?>[] parameterTypes){
        if(! getApplicationContext().containsBean(beanName)) {
            return false;
        }

        Object serviceImpl = SpringContextHolder.getBean(beanName);
        Method method;
        try {
            method = serviceImpl.getClass().getMethod(methodName,parameterTypes);
            if (method == null) {
                return false;
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public static boolean existBean(String beanName) {
        return getApplicationContext().containsBean(beanName);
    }
}
