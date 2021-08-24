package indi.uhyils.entity;

import indi.uhyils.entity.type.Password;
import indi.uhyils.pojo.model.UserDO;
import indi.uhyils.util.MD5Util;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时29分
 */
public class User extends AbstractDoEntity<UserDO> {

    public User(UserDO userDO) {
        super(userDO);
    }

    public boolean checkPassword(Password password) {
        return Objects.equals(password.getPassword(), data.getPassword());
    }

    public void encodePassword() {
        String password = data.getPassword();
        String encodePassword = MD5Util.MD5Encode(password);
        data.setPassword(encodePassword);
    }
}
