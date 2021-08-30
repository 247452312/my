package indi.uhyils.repository;

import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.entity.Token;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 用户仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时46分
 */
public interface UserRepository extends BaseEntityRepository<UserDO, User> {


    /**
     * 获取全部
     *
     * @return
     */
    List<User> findAll();

    /**
     * 在redis中根据token获取用户
     *
     * @return
     */
    User findUserByTokenInRedis(Token token);

    /**
     * 判断token是否存在
     *
     * @param token
     *
     * @return
     */
    Boolean haveToken(Token token);

    /**
     * 尝试登录
     *
     * @param user 包含用户名密码的用户
     *
     * @return
     */
    User checkLogin(User user);

    /**
     * 检查redis是否包含用户id
     *
     * @param userId
     *
     * @return
     */
    Boolean checkCacheUserId(User userId);

    /**
     * 根据id删除用户
     *
     * @param userId
     */
    boolean removeUserInCacheById(User userId);

    /**
     * 向redis中添加user
     *
     * @param token
     * @param user
     */
    void cacheUser(Token token, User user);

    /**
     * 清空redis中token信息
     *
     * @param token
     *
     * @return
     */
    boolean removeUserByToken(Token token);

    /**
     * 检查password是否正确
     *
     * @param user
     * @param password
     */
    void checkPassword(User user, Password password);

}
