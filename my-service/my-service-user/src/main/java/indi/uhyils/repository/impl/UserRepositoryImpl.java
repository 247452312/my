package indi.uhyils.repository.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.UserConvert;
import indi.uhyils.dao.UserDao;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.Token;
import indi.uhyils.pojo.entity.type.UserId;
import indi.uhyils.pojo.entity.type.UserName;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.AssertUtil;
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
        UserDO user = redisPoolHandle.getUser(tokenStr);
        return new User(user);
    }

    @Override
    public Boolean haveToken(Token token) {
        return redisPoolHandle.haveToken(token.getToken());
    }

    @Override
    public User checkLogin(UserName userName, Password password) {
        List<Arg> objects = new ArrayList<>();
        objects.add(new Arg("username", "=", userName.getUserName()));
        objects.add(new Arg("password", "=", password.toMD5Str()));
        ArrayList<UserDO> byArgsNoPage = getDao().getByArgsNoPage(objects);
        AssertUtil.assertTrue(CollectionUtils.isNotEmpty(byArgsNoPage) && byArgsNoPage.size() == 1, "登录失败,用户名或密码不正确!");
        UserDO userDO = byArgsNoPage.get(0);
        return new User(userDO);

    }

    @Override
    public Boolean checkRedisContainUserId(UserId userId) {
        return redisPoolHandle.haveUserId(userId.getId());
    }

    @Override
    public boolean removeUserInRedisById(UserId userId) {
        return redisPoolHandle.removeUserById(userId.getId());
    }

    @Override
    public void addUser(Token token, User user) {
        redisPoolHandle.addUser(token.getToken(), user.toDo());
    }

    @Override
    public boolean removeUserByToken(Token token) {
        return redisPoolHandle.removeByKey(token.getToken());
    }

    @Override
    public void checkPassword(User user, Password password) {
        Integer integer = getDao().checkUserPassword(user.toId().getId(), password.toMD5Str());
        AssertUtil.assertTrue(integer == 1, "密码错误");
    }
}
