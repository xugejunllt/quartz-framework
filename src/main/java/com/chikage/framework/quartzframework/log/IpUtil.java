package com.chikage.framework.quartzframework.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IpUtil {
    private static Logger logger = LoggerFactory.getLogger(IpUtil.class);

    private IpUtil() {
    }

    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;

        try {
            boolean bFindIP = false;
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();

            label38:
            while(true) {
                while(true) {
                    if(!netInterfaces.hasMoreElements() || bFindIP) {
                        break label38;
                    }

                    NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
                    Enumeration ips = ni.getInetAddresses();

                    while(ips.hasMoreElements()) {
                        ip = (InetAddress)ips.nextElement();
                        if(!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
                            bFindIP = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception var6) {
            ;
        }

        if(null != ip) {
            sIP = ip.getHostAddress();
        }

        return sIP;
    }

    public static List<String> getLocalIPS() {
        InetAddress ip = null;
        ArrayList ipList = new ArrayList();

        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();

            while(netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
                Enumeration ips = ni.getInetAddresses();

                while(ips.hasMoreElements()) {
                    ip = (InetAddress)ips.nextElement();
                    if(!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
                        ipList.add(ip.getHostAddress());
                    }
                }
            }
        } catch (Exception var5) {
            logger.error(var5.getMessage());
        }

        return ipList;
    }

    public static String getMacId() {
        String macId = "";
        InetAddress ip = null;
        NetworkInterface ni = null;

        try {
            boolean bFindIP = false;
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();

            label42:
            while(true) {
                while(true) {
                    if(!netInterfaces.hasMoreElements() || bFindIP) {
                        break label42;
                    }

                    ni = (NetworkInterface)netInterfaces.nextElement();
                    Enumeration ips = ni.getInetAddresses();

                    while(ips.hasMoreElements()) {
                        ip = (InetAddress)ips.nextElement();
                        if(!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
                            bFindIP = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception var7) {
            logger.error(var7.getMessage());
        }

        if(null != ip) {
            try {
                macId = getMacFromBytes(ni.getHardwareAddress());
            } catch (SocketException var6) {
                logger.error(var6.getMessage());
            }
        }

        return macId;
    }

    public static List<String> getMacIds() {
        InetAddress ip = null;
        NetworkInterface ni = null;
        ArrayList macList = new ArrayList();

        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();

            while(netInterfaces.hasMoreElements()) {
                ni = (NetworkInterface)netInterfaces.nextElement();
                Enumeration ips = ni.getInetAddresses();

                while(ips.hasMoreElements()) {
                    ip = (InetAddress)ips.nextElement();
                    if(!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
                        macList.add(getMacFromBytes(ni.getHardwareAddress()));
                    }
                }
            }
        } catch (Exception var5) {
            logger.error(var5.getMessage());
        }

        return macList;
    }

    private static String getMacFromBytes(byte[] bytes) {
        StringBuffer mac = new StringBuffer();
        boolean first = false;
        byte[] var4 = bytes;
        int var5 = bytes.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            byte b = var4[var6];
            if(first) {
                mac.append("-");
            }

            byte currentByte = (byte)((b & 240) >> 4);
            mac.append(Integer.toHexString(currentByte));
            currentByte = (byte)(b & 15);
            mac.append(Integer.toHexString(currentByte));
            first = true;
        }

        return mac.toString().toUpperCase();
    }
}
