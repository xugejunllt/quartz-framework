package com.chikage.framework.quartzframework.log;


public class LogTreadLocal {
    public final static String X_TRANSACTION_ID="x-transaction-id";
    private final static ThreadLocal<String> trackingNoThreadLocal = new ThreadLocal<String>();

    public static void setTrackingNo(String trackingNo) {
        LogTreadLocal.trackingNoThreadLocal.set(trackingNo);
    }

    public static String getTrackingNo() {
        String trac = LogTreadLocal.trackingNoThreadLocal.get();
        return trac;
    }

    public static void removeTrackingNo() {
        LogTreadLocal.trackingNoThreadLocal.remove();
    }
}