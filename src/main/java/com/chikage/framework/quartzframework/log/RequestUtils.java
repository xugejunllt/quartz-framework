package com.chikage.framework.quartzframework.log;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestUtils {
    public RequestUtils() {
    }

    public static String getRemoteAddr(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if(StringUtils.isNotBlank(ip)) {
            String[] ips = StringUtils.split(ip, ',');
            if(ips != null) {
                String[] var3 = ips;
                int var4 = ips.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    String tmpip = var3[var5];
                    if(!StringUtils.isBlank(tmpip)) {
                        tmpip = tmpip.trim();
                        if(isIPAddr(tmpip) && !tmpip.startsWith("10.") && !tmpip.startsWith("192.168.") && !"127.0.0.1".equals(tmpip)) {
                            return tmpip.trim();
                        }
                    }
                }
            }
        }

        return getRequestIp(req);
    }

    public static String getRequestIp(HttpServletRequest req) {
        String ip = req.getHeader("x-real-ip");
        if(isIPAddr(ip)) {
            return ip;
        } else {
            ip = req.getRemoteAddr();
            if(ip.indexOf(46) == -1) {
                ip = "127.0.0.1";
            }

            return ip;
        }
    }

    public static String getDomainOfServerName(String host) {
        if(isIPAddr(host)) {
            return null;
        } else {
            String[] names = StringUtils.split(host, '.');
            int len = names.length;
            if(len == 1) {
                return null;
            } else if(len == 3) {
                return makeup(new String[]{names[len - 2], names[len - 1]});
            } else if(len > 3) {
                String dp = names[len - 2];
                return !"com".equalsIgnoreCase(dp) && !"gov".equalsIgnoreCase(dp) && !"net".equalsIgnoreCase(dp) && !"edu".equalsIgnoreCase(dp) && !"org".equalsIgnoreCase(dp)?makeup(new String[]{names[len - 2], names[len - 1]}):makeup(new String[]{names[len - 3], names[len - 2], names[len - 1]});
            } else {
                return host;
            }
        }
    }

    public static boolean isIPAddr(String addr) {
        if(StringUtils.isEmpty(addr)) {
            return false;
        } else {
            String[] ips = StringUtils.split(addr, '.');
            if(ips.length != 4) {
                return false;
            } else {
                try {
                    int ipa = Integer.parseInt(ips[0]);
                    int ipb = Integer.parseInt(ips[1]);
                    int ipc = Integer.parseInt(ips[2]);
                    int ipd = Integer.parseInt(ips[3]);
                    return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0 && ipc <= 255 && ipd >= 0 && ipd <= 255;
                } catch (Exception var6) {
                    return false;
                }
            }
        }
    }

    private static String makeup(String... ps) {
        StringBuilder s = new StringBuilder();

        for(int idx = 0; idx < ps.length; ++idx) {
            if(idx > 0) {
                s.append('.');
            }

            s.append(ps[idx]);
        }

        return s.toString();
    }

    public static int getHttpPort(HttpServletRequest req) {
        try {
            return (new URL(req.getRequestURL().toString())).getPort();
        } catch (MalformedURLException var2) {
            return 80;
        }
    }

    public static Object getUrl(HttpServletRequest request) {
        return request.getRequestURI();
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }
}