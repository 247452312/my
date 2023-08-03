package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.UserAssembler;
import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.LoginDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.request.FindUserByNameQuery;
import indi.uhyils.pojo.entity.Token;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.Visiter;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.UserName;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.service.UserService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时28分
 */
@Service
@ReadWriteMark(tables = {"sys_user"})
public class UserServiceImpl extends AbstractDoService<UserDO, User, UserDTO, UserRepository, UserAssembler> implements UserService {

    @Value("${token.salt}")
    private String salt;

    @Value("${token.encodeRules}")
    private String encodeRules;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private DeptRepository deptRepository;

    @Resource
    private PowerRepository powerRepository;

    @Resource
    private MenuRepository menuRepository;


    public UserServiceImpl(UserAssembler userAssembler, UserRepository userRepository) {
        super(userAssembler, userRepository);
    }

    @Override
    public UserDTO getUserById(Identifier userId) {
        User user = rep.find(userId);
        return assem.toDTO(user);
    }

    @Override
    public String getUserToken(Identifier userId) {
        User user = new User(userId);
        Token token = user.toToken(salt, encodeRules);
        return token.getToken();
    }

    @Override
    public TokenInfo getTokenInfoByToken(Token token) {
        return token.parseToTokenInfo(encodeRules, salt, rep);
    }

    @Override
    public LoginDTO login(UserName userName, Password password) {
        User user = new User(userName, password);
        user.login(rep, salt, encodeRules);

        //检查是否已经登录,如果已经登录,则将之前已登录的挤下来
        user.removeUserInRedis(rep);
        // 登录->加入缓存中
        user.addUserToRedis(rep);
        return LoginDTO.buildLoginSuccess(user.tokenValue(), assem.toDTO(user));
    }

    @Override
    public Boolean logout() {
        User user = new User(assem.toDo(UserInfoHelper.doGet()));
        return user.logout(rep);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> all = rep.findAll();
        // 填充角色
        User.batchInitRole(all, roleRepository, deptRepository, powerRepository, menuRepository);
        return assem.listEntityToDTO(all);
    }

    @Override
    public UserDTO getUserByToken() {
        return UserInfoHelper.doGet();
    }

    @Override
    public String updatePassword(Password oldPassword, Password newPassword) {
        User user = new User(assem.toDo(UserInfoHelper.doGet()));
        //检查密码是否正确
        user.checkPassword(oldPassword, rep);
        // 修改到新密码
        user.changeToNewPassword(newPassword, rep);
        return "true";
    }

    @Override
    public String getNameById(Identifier userId) {
        User user = rep.find(userId);
        return user.toData().map(UserDO::getNickName).orElse(null);
    }

    @Override
    public List<UserDTO> getSampleUserByIds(List<Identifier> userIds) {
        List<User> users = rep.find(userIds);
        return assem.listEntityToDTO(users);
    }


    @Override
    public Boolean applyUser(UserDTO userDTO) {
        User user = assem.toEntity(userDTO);
        user.apply(rep);
        return true;
    }

    @Override
    public Boolean passApply(Identifier request) {
        User user = new User(request.getId());
        user.passApply(rep);
        return true;
    }

    @Override
    public Boolean stopUser(Identifier request) {
        User user = new User(request);
        user.stopUser(rep);
        return true;
    }

    @Override
    public UserDTO getUserByUserName(FindUserByNameQuery request) {
        List<User> users = rep.findUserByUsername(request.getName());
        Asserts.assertTrue(CollectionUtil.isNotEmpty(users), "用户名不存在");
        Asserts.assertTrue(users.size() == 1, "同一个用户名只能有一条数据,{}", request.getName());
        User user = users.get(0);
        return assem.toDTO(user);
    }

    @Override
    public LoginDTO visiterLogin() {
        final Optional<String> userIp = UserInfoHelper.getUserIp();
        Asserts.assertTrue(userIp.isPresent(), "获取用户ip失败");

        final User visiter = new Visiter(userIp.get());
        visiter.login(rep, salt, encodeRules);
        // 登录->游客也加入缓存中
        visiter.addUserToRedis(rep);
        return LoginDTO.buildLoginSuccess(visiter.tokenValue(), assem.toDTO(visiter));
    }

}
