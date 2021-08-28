package indi.uhyils.pojo.entity;

import indi.uhyils.assembler.UserAssembler;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.repository.UserRepository;


/**
 * 登录成功信息
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 09时22分
 */
public class LoginStatus extends AbstractEntity {


    private Token token;

    private User user;

    private boolean success;

    public LoginStatus(Token token, User user) {
        this.token = token;
        this.user = user;
        this.success = true;
    }

    public LoginStatus(DefaultCQE request, UserAssembler assembler) {
        this(new Token(request.getToken()), assembler.toEntity(request.getUser()));
    }

    public LoginStatus() {
        this.success = false;
    }

    public void removeUserInRedis(UserRepository userRepository) {
        //检查是否已经登录,如果已经登录,则将之前已登录的挤下来
        Boolean haveUserId = userRepository.checkCacheUserId(user.toId());

        if (haveUserId != null && haveUserId) {
            userRepository.removeUserInCacheById(user.toId());
        }
    }

    public void addUserToRedis(UserRepository userRepository) {
        userRepository.cacheUser(token, user);
    }

    public UserDO userValue() {
        return user.toDo();
    }

    public String tokenValue() {
        return token.getToken();
    }

    public Boolean logout(UserRepository userRepository) {
        boolean result = userRepository.removeUserByToken(token);
        if (result) {
            result = userRepository.removeUserInCacheById(user.toId());
        }
        return result;
    }

    public void checkPassword(Password password, UserRepository userRepository) {
        userRepository.checkPassword(user, password);
    }

    /**
     * 修改密码
     *
     * @param password
     * @param userRepository
     */
    public void changeToNewPassword(Password password, UserRepository userRepository) {
        user.toDo().setPassword(password.toMD5Str());
        user.onUpdate();
        userRepository.save(user);

    }
}
