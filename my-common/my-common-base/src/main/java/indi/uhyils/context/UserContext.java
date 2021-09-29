package indi.uhyils.context;

import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.DefaultCQEBuildUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 16时27分
 */
public class UserContext {

    private static final ThreadLocal<UserDTO> USER = new ThreadLocal<>();

    private static final ThreadLocal<String> TOKEN = new ThreadLocal<>();

    public static UserDTO setUser(UserDTO userDO) {
        UserDTO lastUser = USER.get();
        USER.set(userDO);
        return lastUser;
    }

    public static UserDTO clean() {
        UserDTO userDO = USER.get();
        USER.remove();
        TOKEN.remove();
        return userDO;
    }

    public static UserDTO get() {
        return USER.get();
    }

    public static String getToken() {
        return TOKEN.get();
    }

    public static void setToken(String token) {
        TOKEN.set(token);
    }

    /**
     * 制作一个cqe来用
     *
     * @return
     */
    public static DefaultCQE makeCQE() {
        UserDTO userDTO = doGet();
        String token = getToken();
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
}
