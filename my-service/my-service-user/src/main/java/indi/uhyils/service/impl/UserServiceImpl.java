package indi.uhyils.service.impl;

import indi.uhyils.assembler.UserAssembler;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.request.UpdatePasswordCommand;
import indi.uhyils.pojo.DTO.response.LoginResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.AbstractDoEntity;
import indi.uhyils.pojo.entity.LoginInfo;
import indi.uhyils.pojo.entity.LoginStatus;
import indi.uhyils.pojo.entity.Token;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.UserId;
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
    public ServiceResult<UserDTO> getUserById(IdQuery request) {
        User user = rep.find(new Identifier(request.getId()));
        UserDTO userDTO = assem.toDTO(user);
        return ServiceResult.buildSuccessResult(userDTO);
    }

    @Override
    public ServiceResult<String> getUserToken(IdQuery request) {
        UserId userId = new UserId(request.getId());
        Token token = userId.toToken(salt, encodeRules);
        return ServiceResult.buildSuccessResult(token.getToken());
    }

    @Override
    public ServiceResult<TokenInfo> getTokenInfoByToken(DefaultCQE request) {
        Token token = new Token(request.getToken());
        TokenInfo tokenInfo = token.parseToTokenInfo(encodeRules, salt, rep);
        return ServiceResult.buildSuccessResult("解密成功", tokenInfo);
    }

    @Override
    public ServiceResult<LoginResponse> login(LoginCommand request) {
        LoginInfo loginInfo = new LoginInfo(new UserName(request.getUsername()), new Password(request.getPassword()));
        LoginStatus loginResult = loginInfo.login(rep, salt, encodeRules);

        //检查是否已经登录,如果已经登录,则将之前已登录的挤下来
        loginResult.removeUserInRedis(rep);
        // 登录->加入缓存中
        loginResult.addUserToRedis(rep);
        return ServiceResult.buildSuccessResult("成功", LoginResponse.buildLoginSuccess(loginResult.tokenValue(), assem.toDTO(loginResult.userValue())));
    }

    @Override
    public ServiceResult<Boolean> logout(DefaultCQE request) {
        LoginStatus loginResult = new LoginStatus(request, assem);
        Boolean success = loginResult.logout(rep);
        return ServiceResult.buildSuccessResult("登出结束", success);
    }

    @Override
    public ServiceResult<List<UserDTO>> getUsers(DefaultCQE request) {
        List<User> all = rep.findAll();
        // 填充角色
        User.batchInitRole(all, roleRepository, deptRepository, powerRepository, menuRepository);
        List<UserDO> userDos = all.stream().map(AbstractDoEntity::toDo).collect(Collectors.toList());
        List<UserDTO> result = userDos.stream().map(assem::toDTO).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("查询成功", result);
    }

    @Override
    public ServiceResult<UserDTO> getUserByToken(DefaultCQE request) {
        return ServiceResult.buildSuccessResult("查询成功", request.getUser());
    }

    @Override
    public ServiceResult<String> updatePassword(UpdatePasswordCommand request) {
        LoginStatus loginStatus = new LoginStatus(request, assem);
        //检查密码是否正确
        loginStatus.checkPassword(new Password(request.getOldPassword()), rep);
        // 修改到新密码
        loginStatus.changeToNewPassword(new Password(request.getNewPassword()), rep);
        return ServiceResult.buildSuccessResult("修改密码成功", "true");
    }

    @Override
    public ServiceResult<String> getNameById(IdQuery request) {
        User user = rep.find(new Identifier(request.getId()));
        return ServiceResult.buildSuccessResult("查询成功", user.toDo().getNickName());
    }
}
