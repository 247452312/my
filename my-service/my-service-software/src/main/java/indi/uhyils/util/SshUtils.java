package indi.uhyils.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import indi.uhyils.pojo.model.ServerDO;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * ssh类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 16时52分
 */
public class SshUtils {

    public static Boolean testConn(String ip, Integer port, String username, String password) {
        Connection conn = null;
        Session ses = null;
        try {
            conn = new Connection(ip, port);
            conn.connect();

            boolean connect = conn.authenticateWithPassword(username, password);
            if (!connect) {
                return Boolean.FALSE;
            }
            ses = conn.openSession();
            ses.execCommand("pwd");
            InputStream stdout = new StreamGobbler(ses.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            String line = br.readLine();
            if (StringUtils.isEmpty(line)) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            LogUtil.error(SshUtils.class, e);
            return Boolean.FALSE;
        } finally {
            if (ses != null) {
                ses.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static String execCommandBySsh(ServerDO serverEntity, String command) {
        return execCommandBySsh(serverEntity.getIp(), serverEntity.getPort(), serverEntity.getUsername(), serverEntity.getPassword(), command);
    }

    public static String execCommandBySsh(String ip, Integer port, String username, String password, String command) {
        Connection conn = null;
        Session ses = null;
        try {
            conn = new Connection(ip, port);
            conn.connect();
            boolean connect = conn.authenticateWithPassword(username, password);
            if (!connect) {
                return null;
            }
            ses = conn.openSession();
            ses.execCommand(command);
            return getResult(ses.getStdout());
        } catch (Exception e) {
            LogUtil.error(SshUtils.class, e);
            return null;
        } finally {
            if (ses != null) {
                ses.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void execCommandBySshNoResponse(String ip, Integer port, String username, String password, String command) {
        Connection conn = null;
        Session ses = null;
        try {
            conn = new Connection(ip, port);
            conn.connect();
            boolean connect = conn.authenticateWithPassword(username, password);
            if (!connect) {
                return;
            }
            ses = conn.openSession();
            ses.execCommand(command);
        } catch (Exception e) {
            LogUtil.error(SshUtils.class, e);
        } finally {
            if (ses != null) {
                ses.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private static String getResult(InputStream in) {
        InputStream stdout = new StreamGobbler(in);
        StringBuilder buffer = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line).append("\n");
            }
        } catch (IOException e) {
            LogUtil.error(SshUtils.class, "解析脚本出错：" + e.getMessage());
        }
        return buffer.toString();
    }
}
