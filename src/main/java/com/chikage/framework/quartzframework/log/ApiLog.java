package com.chikage.framework.quartzframework.log;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

public class ApiLog {
    private static Logger logger = LoggerFactory.getLogger(ApiLog.class);

    public static void infoConvertJson(String tracking, String appName, HttpServletRequest request, long startTime, long endTime, Object requestData, Object responseData) {
        Object responseDataConvert = null;
        if (responseData instanceof String) {
            responseDataConvert = responseData;
        } else {
            responseDataConvert = JSON.toJSONString(responseData);
        }
        Object requestDataConvert = null;
        if (requestData instanceof String) {
            requestDataConvert = requestData;
        } else {
            requestDataConvert = JSON.toJSONString(requestData);
        }
        logger.info("createAt:{} appName:{} trackingNo:{} startTime:{} endTime:{} elapsed-time:{} url:{} userAgent:{} requestIp:{} realIp:{} serverIp:{} request:{} response:{}", DateUtil.convert2String(Calendar.getInstance().getTime(), DateUtil.FORMAT_YYYY_MM_DD_HHMMSS), appName, tracking, startTime, endTime, endTime - startTime, RequestUtils.getUrl(request), RequestUtils.getUserAgent(request), RequestUtils.getRequestIp(request), RequestUtils.getRemoteAddr(request), IpUtil.getLocalIP(), requestDataConvert, responseDataConvert);
    }

    public static void infoConvertJson(String methodName, String message, String tracking, String appName, HttpServletRequest request, long startTime, long endTime, Object requestData, Object responseData) {
        Object responseDataConvert = null;
        if (responseData instanceof String) {
            responseDataConvert = responseData;
        } else {
            responseDataConvert = JSON.toJSONString(responseData);
        }
        Object requestDataConvert = null;
        if (requestData instanceof String) {
            requestDataConvert = requestData;
        } else {
            requestDataConvert = JSON.toJSONString(requestData);
        }
        logger.info("methodName:{} message:{} appName:{} trackingNo:{} startTime:{} endTime:{} url:{} userAgent:{} requestIp:{} realIp:{} serverIp:{} request:{} response:{}", methodName, message, appName, tracking, startTime, endTime, RequestUtils.getUrl(request), RequestUtils.getUserAgent(request), RequestUtils.getRequestIp(request), RequestUtils.getRemoteAddr(request), IpUtil.getLocalIP(), requestDataConvert, responseDataConvert);
    }

    public static void info(String msg, Object... objects) {
        logger.info(msg, objects);
    }


}
