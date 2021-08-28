package indi.uhyils.context;

import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.util.DefaultRequestBuildUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 16时27分
 */
public class UserContext {

    private static final ThreadLocal<UserDTO> USER = new ThreadLocal<>();


    public static UserDTO setUser(UserDTO userDO) {
        UserDTO lastUser = USER.get();
        USER.set(userDO);
        return lastUser;
    }

    public static UserDTO clean() {
        UserDTO userDO = USER.get();
        USER.remove();
        return userDO;
    }

    public static UserDTO get() {
        return USER.get();
    }

    public static UserDTO doGet() {
        UserDTO userDO = USER.get();
        if (userDO == null) {
            USER.set(DefaultRequestBuildUtil.getAdminDefaultRequest().getUser());
        }
        return USER.get();
    }
}
