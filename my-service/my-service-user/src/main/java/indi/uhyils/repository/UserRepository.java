package indi.uhyils.repository;

import indi.uhyils.entity.User;
import indi.uhyils.entity.type.Token;
import indi.uhyils.repository.base.BaseRepository;
import java.util.List;

/**
 * 用户仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时46分
 */
public interface UserRepository extends BaseRepository<User> {


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
}
