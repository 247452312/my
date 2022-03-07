package indi.uhyils.repository.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.UserAssembler;
import indi.uhyils.dao.UserDao;
import indi.uhyils.enum_.Symbol;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.entity.Token;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.Asserts;
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
    public User findUserByIdInRedis(Identifier userId) {
        UserDTO user = redisPoolHandle.getUser(userId.getId());
        return assembler.toEntity(user);
    }

    @Override
    public Boolean haveToken(Token token) {
        return redisPoolHandle.haveToken(token.getToken());
    }

    @Override
    public User checkLogin(User user) {
        List<Arg> objects = new ArrayList<>();
        objects.add(new Arg(UserDTO::getUsername, "=", user.username().getUserName()));
        objects.add(new Arg(UserDTO::getPassword, "=", user.password().toMD5Str()));
        List<UserDO> byArgsNoPage = dao.selectList(Symbol.makeWrapper(objects));
        Asserts.assertTrue(CollectionUtils.isNotEmpty(byArgsNoPage) && byArgsNoPage.size() == 1, "登录失败,用户名或密码不正确!");
        UserDO userDO = byArgsNoPage.get(0);
        return new User(userDO);
    }

    @Override
    public Boolean checkCacheUserId(User userId) {
        return redisPoolHandle.haveUserId(userId.getUnique().getId());
    }

    @Override
    public boolean removeUserInCacheById(User userId) {
        return redisPoolHandle.removeUserById(userId.getUnique().getId());
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
        Integer integer = dao.checkUserPassword(user.getUnique().getId(), password.toMD5Str());
        Asserts.assertTrue(integer == 1, "密码错误");
    }
}
