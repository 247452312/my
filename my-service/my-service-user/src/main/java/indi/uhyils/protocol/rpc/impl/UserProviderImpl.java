package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.UserDao;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.AbstractDoEntity;
import indi.uhyils.pojo.entity.LoginInfo;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.ag.LoginStatus;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.Token;
import indi.uhyils.pojo.entity.type.UserId;
import indi.uhyils.pojo.entity.type.UserName;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.request.LoginRequest;
import indi.uhyils.pojo.DTO.request.UpdatePasswordRequest;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.request.base.ObjRequest;
import indi.uhyils.pojo.DTO.response.LoginResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.protocol.rpc.provider.UserProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时51分
 */
@RpcService
@ReadWriteMark(tables = {"sys_user"})
public class UserProviderImpl extends BaseDefaultProvider<UserDTO> implements UserProvider {

    @Override
    @NoToken
    @ReadWriteMark(tables = {"sys_user", "sys_role", "sys_dept", "sys_role_dept", "sys_power", "sys_dept_power"})
    public ServiceResult<UserDO> getUserById(IdRequest idRequest) {

        //缓存
        User user = userRepository.findUserByTokenInRedis(new Token(idRequest.getToken()));

        if (user != null) {
            return ServiceResult.buildSuccessResult("查询成功", user.toDo());
        }
        user = userRepository.find(new Identifier(idRequest.getId()));
        if (user.isEmpty()) {
            return ServiceResult.buildFailedResult("查询失败", null);
        }
        user.initRole(roleRepository, deptRepository, powerRepository);
        return ServiceResult.buildSuccessResult("查询成功", user.toDo());

    }

    @Override
    @NoToken
    @ReadWriteMark(cacheType = CacheTypeEnum.NOT_TYPE)
    public ServiceResult<String> getUserToken(IdRequest request) {
        UserId userId = new UserId(request.getId());
        return ServiceResult.buildSuccessResult("token生成成功", userId.toToken(salt, encodeRules).getToken());
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<UserDO> insert) throws Exception {
        User entity = new User(insert.getData());
        entity.encodePassword();
        userRepository.save(entity);
        return ServiceResult.buildSuccessResult("插入成功", 1);
    }


    @Override
    @NoToken
    public ServiceResult<TokenInfo> getTokenInfoByToken(DefaultRequest request) {
        Token token = new Token(request.getToken());
        TokenInfo tokenInfo = token.parseToTokenInfo(encodeRules, salt, userRepository);
        return ServiceResult.buildSuccessResult("解密成功", tokenInfo);
    }

    @Override
    @NoToken
    public ServiceResult<LoginResponse> login(LoginRequest userRequest) {
        LoginInfo loginInfo = new LoginInfo(new UserName(userRequest.getUsername()), new Password(userRequest.getPassword()));
        LoginStatus loginResult = loginInfo.login(userRepository, salt, encodeRules);

        //检查是否已经登录,如果已经登录,则将之前已登录的挤下来
        loginResult.removeUserInRedis(userRepository);
        // 登录->加入缓存中
        loginResult.addUserToRedis(userRepository);

        return ServiceResult.buildSuccessResult("成功", LoginResponse.buildLoginSuccess(loginResult.tokenValue(), loginResult.userValue()));
    }

    @Override
    public ServiceResult<Boolean> logout(DefaultRequest request) {
        LoginStatus loginResult = new LoginStatus(request);
        Boolean success = loginResult.logout(userRepository);
        return ServiceResult.buildSuccessResult("登出结束", success);
    }

    @Override
    public ServiceResult<ArrayList<UserDO>> getUsers(DefaultRequest request) {
        List<User> all = userRepository.findAll();
        // 填充角色
        User.batchInitRole(all, roleRepository, deptRepository, powerRepository);
        List<UserDO> userDos = all.stream().map(AbstractDoEntity::toDo).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("查询成功", new ArrayList<>(userDos));
    }

    @Override
    public ServiceResult<UserDO> getUserByToken(DefaultRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", request.getUser());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<String> updatePassword(UpdatePasswordRequest request) {
        LoginStatus loginStatus = new LoginStatus(request);
        //检查密码是否正确
        loginStatus.checkPassword(new Password(request.getOldPassword()), userRepository);
        // 修改到新密码
        loginStatus.changeToNewPassword(new Password(request.getNewPassword()), userRepository);
        return ServiceResult.buildSuccessResult("修改密码成功", "true");
    }

    @Override
    public ServiceResult<String> getNameById(IdRequest request) {
        User user = userRepository.find(new Identifier(request.getId()));
        return ServiceResult.buildSuccessResult("查询成功", user.toDo().getNickName());
    }

    @Override
    public UserDao getDao() {
        return dao;
    }


}
