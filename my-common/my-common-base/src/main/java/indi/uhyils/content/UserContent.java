package indi.uhyils.content;

import indi.uhyils.pojo.model.UserDO;
import indi.uhyils.util.DefaultRequestBuildUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 16时27分
 */
public class UserContent {

    private static final ThreadLocal<UserDO> USER = new ThreadLocal<>();


    public static UserDO setUser(UserDO userDO) {
        UserDO lastUser = USER.get();
        USER.set(userDO);
        return lastUser;
    }

    public static UserDO clean() {
        UserDO userDO = USER.get();
        USER.remove();
        return userDO;
    }

    public static UserDO get() {
        return USER.get();
    }

    public static UserDO doGet() {
        UserDO userDO = USER.get();
        if (userDO == null) {
            USER.set(DefaultRequestBuildUtil.getAdminDefaultRequest().getUser());
        }
        return USER.get();
    }
}
