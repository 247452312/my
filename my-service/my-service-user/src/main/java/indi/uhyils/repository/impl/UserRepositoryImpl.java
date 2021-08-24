package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.convert.UserConvert;
import indi.uhyils.dao.UserDao;
import indi.uhyils.entity.User;
import indi.uhyils.entity.type.Token;
import indi.uhyils.pojo.model.UserDO;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时27分
 */
@Repository
public class UserRepositoryImpl extends AbstractRepository<User, UserDO, UserDao> implements UserRepository {


    private final RedisPoolHandle redisPoolHandle;

    protected UserRepositoryImpl(UserConvert convert, UserDao dao, RedisPoolHandle redisPoolHandle) {
        super(convert, dao);
        this.redisPoolHandle = redisPoolHandle;
    }

    @Override
    public List<User> findAll() {
        ArrayList<UserDO> all = getDao().getAll();
        return all.stream().map(t -> getConvert().doToEntity(t)).collect(Collectors.toList());
    }


    /**
     * 在redis中根据token获取用户
     *
     * @return
     */
    @Override
    public User findUserByTokenInRedis(Token token) {
        String tokenStr = token.getToken();
        return getConvert().doToEntity(redisPoolHandle.getUser(tokenStr));
    }
}
