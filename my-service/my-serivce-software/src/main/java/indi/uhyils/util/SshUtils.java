package indi.uhyils.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
                return false;
            }
            ses = conn.openSession();
            ses.execCommand("pwd");
            InputStream stdout = new StreamGobbler(ses.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            String line = br.readLine();
            if (StringUtils.isBlank(line)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            LogUtil.error(SshUtils.class, e.getMessage());
            return false;
        } finally {
            if (ses != null) {
                ses.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
