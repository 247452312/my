package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.UserAssembler;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.request.UpdatePasswordCommand;
import indi.uhyils.pojo.DTO.response.LoginDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.pojo.entity.AbstractDoEntity;
import indi.uhyils.pojo.entity.Token;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.UserName;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DeptRepository deptRepository;


    @Autowired
    private PowerRepository powerRepository;


    @Autowired
    private MenuRepository menuRepository;


    public UserServiceImpl(UserAssembler userAssembler, UserRepository userRepository) {
        super(userAssembler, userRepository);
    }

    @Override
    public UserDTO getUserById(IdQuery request) {
        User user = rep.find(new Identifier(request.getId()));
        return assem.toDTO(user);
    }

    @Override
    public String getUserToken(IdQuery request) {
        User userId = new User(request.getId());
        Token token = userId.toToken(salt, encodeRules);
        return token.getToken();
    }

    @Override
    public TokenInfo getTokenInfoByToken(DefaultCQE request) {
        Token token = new Token(request.getToken());
        TokenInfo tokenInfo = token.parseToTokenInfo(encodeRules, salt, rep);
        return tokenInfo;
    }

    @Override
    public LoginDTO login(LoginCommand request) {
        User user = new User(new UserName(request.getUsername()), new Password(request.getPassword()));
        user.login(rep, salt, encodeRules);

        //检查是否已经登录,如果已经登录,则将之前已登录的挤下来
        user.removeUserInRedis(rep);
        // 登录->加入缓存中
        user.addUserToRedis(rep);
        return LoginDTO.buildLoginSuccess(user.tokenValue(), assem.toDTO(user));
    }

    @Override
    public Boolean logout(DefaultCQE request) {
        User user = new User(assem.toDo(request.getUser()));
        return user.logout(rep);
    }

    @Override
    public List<UserDTO> getUsers(DefaultCQE request) {
        List<User> all = rep.findAll();
        // 填充角色
        User.batchInitRole(all, roleRepository, deptRepository, powerRepository, menuRepository);
        return assem.listEntityToDTO(all);
    }

    @Override
    public UserDTO getUserByToken(DefaultCQE request) {
        return request.getUser();
    }

    @Override
    public String updatePassword(UpdatePasswordCommand request) {
        User user = new User(assem.toDo(request.getUser()));
        //检查密码是否正确
        user.checkPassword(new Password(request.getOldPassword()), rep);
        // 修改到新密码
        user.changeToNewPassword(new Password(request.getNewPassword()), rep);
        return "true";
    }

    @Override
    public String getNameById(IdQuery request) {
        User user = rep.find(new Identifier(request.getId()));
        return user.toDo().getNickName();
    }

    @Override
    public List<UserDTO> getSampleUserByIds(IdsQuery request) {
        List<User> result = rep.findNoPage(request);
        return assem.listEntityToDTO(result);
    }

}
