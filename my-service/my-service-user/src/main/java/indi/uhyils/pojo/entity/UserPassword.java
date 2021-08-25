package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Password;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时56分
 */
public class UserPassword extends AbstractEntity {

    private Password password;

    public UserPassword(Password password) {
        this.password = password;
    }

    public boolean checkPassword(User user) {
        return user.checkPassword(this.password);
    }
}
