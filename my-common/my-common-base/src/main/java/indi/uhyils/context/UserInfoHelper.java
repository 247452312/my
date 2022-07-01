package indi.uhyils.context;

import indi.uhyils.MyThreadLocal;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.DefaultCQEBuildUtil;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 16时27分
 */
public class UserInfoHelper {

    /**
     * mysql协议的角色id
     */
    public static final Long MYSQL_ROLE_ID = -1L;

    /**
     * 用户在rpc的header中的用户ip的key
     */
    public static final String USER_IP_RPC_KEY = "user_ip";

    private static final MyThreadLocal<UserDTO> USER = new MyThreadLocal<>();

    private static final MyThreadLocal<String> TOKEN = new MyThreadLocal<>();

    private static final MyThreadLocal<String> IP = new MyThreadLocal<>();

    public static UserDTO setUser(UserDTO userDO) {
        UserDTO lastUser = USER.get();
        USER.set(userDO);
        return lastUser;
    }

    public static UserDTO clean() {
        UserDTO userDO = USER.get();
        USER.remove();
        TOKEN.remove();
        cleanIp();
        return userDO;
    }

    public static void cleanIp() {
        IP.remove();
    }

    public static Optional<UserDTO> get() {
        return Optional.ofNullable(USER.get());
    }

    public static Optional<String> getToken() {
        return Optional.ofNullable(TOKEN.get());
    }

    public static void setToken(String token) {
        TOKEN.set(token);
    }

    public static void setIp(String ip) {
        IP.set(ip);
    }

    /**
     * 制作一个cqe来用
     *
     * @return
     */
    public static DefaultCQE makeCQE() {
        UserDTO userDTO = doGet();
        String token = getToken().orElse(null);
        DefaultCQE defaultCQE = new DefaultCQE();
        defaultCQE.setUser(userDTO);
        defaultCQE.setToken(token);
        return defaultCQE;
    }

    public static UserDTO doGet() {
        UserDTO userDO = USER.get();
        if (userDO == null) {
            USER.set(DefaultCQEBuildUtil.getAdminUserDTO());
        }
        return USER.get();
    }

    public static Optional<String> getUserIp() {
        return Optional.ofNullable(IP.get());
    }
}
