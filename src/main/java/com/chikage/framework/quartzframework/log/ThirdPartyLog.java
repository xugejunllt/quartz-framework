package com.chikage.framework.quartzframework.log;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.freemud.base.log
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @author: aiqi.gong
 * @date: 2017/6/21 14:33
 */
public class ThirdPartyLog {
    private static Logger logger = LoggerFactory.getLogger(ThirdPartyLog.class);

    public static void infoConvertJson(String trackingNo, String appName, long startTime, long endTime, String url, String action, Object requestData, Object responseData, String responseCode, String message) {
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
        logger.info("createAt:{} appName:{} trackingNo:{} startTime:{} endTime:{} elapsed-time:{} url:{} action:{} serverIp:{} request:{} response:{} responseCode:{} message:{}", DateUtil.convert2String(Calendar.getInstance().getTime(), DateUtil.FORMAT_YYYY_MM_DD_HHMMSS), appName, trackingNo, startTime, endTime, endTime - startTime, url, action, IpUtil.getLocalIP(), requestDataConvert, responseDataConvert, responseCode, message);
    }
}
