package indi.uhyils.util;

import indi.uhyils.context.MyContext;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 09时15分
 */
public final class IpUtil {

    private static final String LEGAL_LOCAL_IP_PROPERTY = "java.net.preferIPv6Addresses";

    private static final String CLIENT_LOCAL_IP_PROPERTY = "indi.uhyils.rpc.ip";

    private static final String DEFAULT_SOLVE_FAILED_RETURN = "resolve_failed";

    private static final String CLIENT_LOCAL_PREFER_HOSTNAME_PROPERTY = "indi.uhyils.rpc.local.preferHostname";

    private static String localIp;

    private IpUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getIp() {
        if (!StringUtil.isEmpty(localIp)) {
            return localIp;
        }

        if (System.getProperties().containsKey(CLIENT_LOCAL_IP_PROPERTY)) {
            return localIp = System.getProperty(CLIENT_LOCAL_IP_PROPERTY, getAddress());
        }

        String ip = getAddress();

        return localIp = ip;

    }

    private static String getAddress() {
        InetAddress inetAddress = findFirstNonLoopbackAddress();
        if (inetAddress == null) {
            return DEFAULT_SOLVE_FAILED_RETURN;
        }

        boolean preferHost = Boolean.parseBoolean(System.getProperty(CLIENT_LOCAL_PREFER_HOSTNAME_PROPERTY));
        return preferHost ? inetAddress.getHostName() : inetAddress.getHostAddress();
    }

    private static InetAddress findFirstNonLoopbackAddress() {
        InetAddress result = null;

        try {
            int lowest = Integer.MAX_VALUE;
            for (Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
                nics.hasMoreElements(); ) {
                NetworkInterface ifc = nics.nextElement();
                if (ifc.isUp()) {
                    if (ifc.getIndex() < lowest || result == null) {
                        lowest = ifc.getIndex();
                    } else {
                        continue;
                    }

                    for (Enumeration<InetAddress> addrs = ifc.getInetAddresses(); addrs.hasMoreElements(); ) {
                        InetAddress address = addrs.nextElement();
                        boolean isLegalIpVersion =
                            Boolean.parseBoolean(System.getProperty(LEGAL_LOCAL_IP_PROPERTY))
                                ? address instanceof Inet6Address : address instanceof Inet4Address;
                        if (isLegalIpVersion && !address.isLoopbackAddress()) {
                            result = address;
                        }
                    }

                }
            }
        } catch (IOException ex) {
            //ignore
        }

        if (result != null) {
            return result;
        }

        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            //ignore
        }

        return null;

    }

    /**
     * 获取请求方的真实ip
     *
     * @param request
     *
     * @return
     */
    public static String getServletIP(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || MyContext.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || MyContext.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || MyContext.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || MyContext.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || MyContext.UN_KNOW.equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
