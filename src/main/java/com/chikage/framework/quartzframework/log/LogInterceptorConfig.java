package com.chikage.framework.quartzframework.log;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.freemud.base.interceptor
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: aiqi.gong
 * @date: 2017/7/8 15:34
 */
@Configuration
public class LogInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
       // registry.addInterceptor(new HeartbeatInterceptor()).addPathPatterns("/**").excludePathPatterns("/adapter/downloadRsaKey");
        registry.addInterceptor(new LogIdInterceptor()).addPathPatterns("/**");

    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        HttpServletRequestReplacedFilter requestReplacedFilter = new HttpServletRequestReplacedFilter();
//        registrationBean.setFilter(requestReplacedFilter);
//        return registrationBean;
//    }

}
