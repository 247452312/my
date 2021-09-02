package indi.uhyils.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 18时28分
 */
public final class SocketUtil {

    private SocketUtil() {
    }

    /**
     * 判断是否可以连接
     *
     * @param hostName
     * @param port
     *
     * @return
     */
    public static boolean canConnect(String hostName, Integer port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(hostName, port));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
