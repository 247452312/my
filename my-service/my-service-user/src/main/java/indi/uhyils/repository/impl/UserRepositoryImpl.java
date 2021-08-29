package indi.uhyils.repository.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.UserAssembler;
import indi.uhyils.dao.UserDao;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.Arg;
import indi.uhyils.pojo.entity.Token;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.UserId;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.UserName;
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
public class UserRepositoryImpl extends AbstractRepository<User, UserDO, UserDao, UserDTO, UserAssembler> implements UserRepository {


    private final RedisPoolHandle redisPoolHandle;

    protected UserRepositoryImpl(UserAssembler assembler, UserDao dao, RedisPoolHandle redisPoolHandle) {
        super(assembler, dao);
        this.redisPoolHandle = redisPoolHandle;
    }

    @Override
    public List<User> findAll() {
        ArrayList<UserDO> all = dao.getAll();
        return all.stream().map(assembler::toEntity).collect(Collectors.toList());
    }


    /**
     * 在redis中根据token获取用户
     *
     * @return
     */
    @Override
    public User findUserByTokenInRedis(Token token) {
        String tokenStr = token.getToken();
        UserDTO userDto = redisPoolHandle.getUser(tokenStr);
        return assembler.toEntity(userDto);
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
        ArrayList<UserDO> byArgsNoPage = dao.getByArgsNoPage(objects, null);
        AssertUtil.assertTrue(CollectionUtils.isNotEmpty(byArgsNoPage) && byArgsNoPage.size() == 1, "登录失败,用户名或密码不正确!");
        UserDO userDO = byArgsNoPage.get(0);
        return new User(userDO);
    }

    @Override
    public Boolean checkCacheUserId(UserId userId) {
        return redisPoolHandle.haveUserId(userId.getId());
    }

    @Override
    public boolean removeUserInCacheById(UserId userId) {
        return redisPoolHandle.removeUserById(userId.getId());
    }

    @Override
    public void cacheUser(Token token, User user) {
        redisPoolHandle.addUser(token.getToken(), assembler.toDTO(user));
    }

    @Override
    public boolean removeUserByToken(Token token) {
        return redisPoolHandle.removeByKey(token.getToken());
    }

    @Override
    public void checkPassword(User user, Password password) {
        Integer integer = dao.checkUserPassword(user.toId().getId(), password.toMD5Str());
        AssertUtil.assertTrue(integer == 1, "密码错误");
    }
}
