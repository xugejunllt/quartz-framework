package com.chikage.framework.quartzframework.log;

import ch.qos.logback.classic.Level;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.freemud.base.log
 * @Description: 异常日志打印
 * @author: aiqi.gong
 * @date: 2017/6/21 10:43
 * @Copyright: 2017 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目
 */
public class ErrorLog {
    private static Logger logger = LoggerFactory.getLogger(ErrorLog.class);


    public static void printLog(String appName, String trackingNo, String message, Exception ex, Level level) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        String exception = baos.toString();
        printLog(appName, trackingNo, message, exception, level);
    }

    public static void printLog(String appName, String trackingNo, String message, Object object, Level level) {
        logger.error("createAt:{} appName:{} trackingNo:{} level:{} message:{} stackTrack:{}", DateUtil.convert2String(Calendar.getInstance().getTime(), DateUtil.FORMAT_YYYY_MM_DD_HHMMSS), appName, trackingNo, level.levelStr, message, JSONObject.toJSONString(object));
    }

    public static void errorConvertJson(String methodName, String message, String tracking, String appName, long startTime, long endTime,String url, Object requestData, Exception ex) {
        Object responseDataConvert = null;
        Object requestDataConvert = null;
        if (!org.springframework.util.StringUtils.isEmpty(requestData)){
            if (requestData instanceof String) {
                requestDataConvert = requestData;
            } else {
                requestDataConvert = JSON.toJSONString(requestData);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        String exception = baos.toString();
        logger.error("methodName:{} message:{} appName:{} trackingNo:{} startTime:{} endTime:{} url:{} serverIp:{} request:{} response:{}", methodName, message, appName, tracking, startTime, endTime,url, IpUtil.getLocalIP(), requestDataConvert, exception);
    }

    public static void errorConvertJson(String methodName, String message, String tracking, String appName, HttpServletRequest request, long startTime, long endTime, Object requestData, Exception ex) {
        Object responseDataConvert = null;
        Object requestDataConvert = null;
        if (requestData instanceof String) {
            requestDataConvert = requestData;
        } else {
            requestDataConvert = JSON.toJSONString(requestData);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        String exception = baos.toString();
        logger.error("methodName:{} message:{} appName:{} trackingNo:{} startTime:{} endTime:{} url:{} userAgent:{} requestIp:{} realIp:{} serverIp:{} request:{} response:{}", methodName, message, appName, tracking, startTime, endTime, RequestUtils.getUrl(request), RequestUtils.getUserAgent(request), RequestUtils.getRequestIp(request), RequestUtils.getRemoteAddr(request), IpUtil.getLocalIP(), requestDataConvert, exception);
    }
    public static void errorConvertJson(String appName, String trackingNo, Class logClass, String message, Exception ex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        String exception = baos.toString();
        printLog(appName, trackingNo, logClass, message, exception, Level.ERROR);
    }

    public static void printLog(String appName, String trackingNo, Class logClass, String message, Object object, Level level) {
        logger.info("createAt:{} ,appName:{} ,trackingNo:{} ,className:{} ,level:{} ,messasge:{} ,stackTrack:{}", DateUtil.convert2String(Calendar.getInstance().getTime(), DateUtil.FORMAT_YYYY_MM_DD_HHMMSS), appName, trackingNo, logClass, level.levelStr, message, JSONObject.toJSONString(object));
    }
}
