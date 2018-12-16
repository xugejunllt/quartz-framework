package com.chikage.framework.quartzframework.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";


    public DateUtil() {
    }


    public static String convert2String(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);

        try {
            return formater.format(date);
        } catch (Exception var4) {
            return null;
        }
    }


}
