package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.UserName;
import indi.uhyils.repository.UserRepository;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 09时15分
 */
public class LoginInfo extends AbstractEntity {

    private UserName username;

    private Password password;

    public LoginInfo(UserName username, Password password) {
        this.username = username;
        this.password = password;
    }

    public LoginStatus login(UserRepository userRepository, String salt, String encodeRules) {

        /*查询是否正确*/
        User user = userRepository.checkLogin(username, password);
        return new LoginStatus(user.parseToken(salt, encodeRules), user);
    }

    public UserName getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}
