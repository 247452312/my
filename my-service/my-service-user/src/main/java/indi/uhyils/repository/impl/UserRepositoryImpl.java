package indi.uhyils.repository.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.UserAssembler;
import indi.uhyils.dao.UserDao;
import indi.uhyils.enums.Symbol;
import indi.uhyils.enums.UserStatusEnum;
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
import java.util.Optional;
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
        final Optional<UserDTO> userDTOOpt = redisPoolHandle.getUser(tokenStr);
        if (!userDTOOpt.isPresent()) {
            return null;
        }
        return assembler.toEntity(userDTOOpt.get());
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
        objects.add(new Arg(UserDTO::getPassword, "=", user.password().encode()));
        List<UserDO> byArgsNoPage = dao.selectList(Symbol.makeWrapper(objects));
        Asserts.assertTrue(CollectionUtils.isNotEmpty(byArgsNoPage) && byArgsNoPage.size() == 1, "登录失败,用户名或密码不正确!");
        UserDO userDO = byArgsNoPage.get(0);
        Asserts.assertEqual(UserStatusEnum.parse(userDO.getStatus()), UserStatusEnum.USING);
        return new User(userDO);
    }

    @Override
    public Boolean checkCacheUserId(User userId) {
        final Optional<Long> aLong = userId.getUnique().map(Identifier::getId);
        return aLong.map(redisPoolHandle::haveUserId).orElse(false);
    }

    @Override
    public boolean removeUserInCacheById(User userId) {
        final Optional<Identifier> unique = userId.getUnique();
        return unique.map(t -> redisPoolHandle.removeUserById(t.getId())).orElse(false);
    }

    @Override
    public void cacheUser(Token token, User user) {
        final UserDTO userDTO = assembler.toDTO(user);
        Asserts.assertTrue(userDTO != null, "没有找到用户信息.无法进行缓存");
        redisPoolHandle.addUser(token.getToken(), userDTO);
    }

    @Override
    public boolean removeUserByToken(Token token) {
        return redisPoolHandle.removeByKey(token.getToken());
    }

    @Override
    public void checkPassword(User user, Password password) {
        final Optional<Identifier> unique = user.getUnique();
        final Integer integer = unique.map(t -> dao.checkUserPassword(t.getId(), password.encode())).orElse(0);
        Asserts.assertTrue(integer == 1, "密码错误");
    }

    @Override
    public boolean checkUserNameRepeat(User user) {
        final Optional<UserDO> userDOOpt = user.toData();
        Asserts.assertTrue(userDOOpt.isPresent(), "用户信息错误,无法进行分析用户名称是否重复");
        UserDO userDO = userDOOpt.get();
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserDO::getUsername, userDO.getUsername());
        // 停用的用户不算
        queryWrapper.in(UserDO::getStatus, UserStatusEnum.USING.getCode(), UserStatusEnum.APPLYING.getCode());
        return dao.selectCount(queryWrapper) != 0;
    }

    @Override
    public List<User> findUserByUsername(String name) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserDO::getUsername, name);
        List<UserDO> userDOS = dao.selectList(queryWrapper);
        return assembler.listToEntity(userDOS);
    }
}
