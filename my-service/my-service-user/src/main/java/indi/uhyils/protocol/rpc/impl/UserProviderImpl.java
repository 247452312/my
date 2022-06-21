package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.NoLogin;
import indi.uhyils.annotation.Public;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.LoginDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.ApplyUserCommand;
import indi.uhyils.pojo.DTO.request.FindUserByNameQuery;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.request.UpdatePasswordCommand;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.pojo.entity.Token;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.UserName;
import indi.uhyils.protocol.rpc.UserProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时51分
 */
@RpcService
public class UserProviderImpl extends BaseDefaultProvider<UserDTO> implements UserProvider {


    @Autowired
    private UserService service;

    @Override
    public ServiceResult<UserDTO> getUserById(IdQuery request) {
        Identifier userId = new Identifier(request.getId());
        UserDTO result = service.getUserById(userId);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<String> getUserToken(IdQuery request) {
        Identifier userId = new Identifier(request.getId());
        String result = service.getUserToken(userId);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<TokenInfo> getTokenInfoByToken(DefaultCQE request) {
        Token token = new Token(request.getToken());
        TokenInfo result = service.getTokenInfoByToken(token);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    @NoLogin
    public ServiceResult<LoginDTO> login(LoginCommand request) {
        UserName username = new UserName(request.getUsername());
        Password password = new Password(request.getPassword());
        LoginDTO result = service.login(username, password);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    @Public
    public ServiceResult<LoginDTO> visiterLogin(AbstractCommand request) {
        LoginDTO loginDTO = service.visiterLogin();
        return ServiceResult.buildSuccessResult(loginDTO);
    }

    @Override
    public ServiceResult<Boolean> logout(DefaultCQE request) {
        Boolean result = service.logout();
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<UserDTO>> getUsers(DefaultCQE request) {
        List<UserDTO> result = service.getUsers();
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<UserDTO> getUserByToken(DefaultCQE request) {
        UserDTO result = service.getUserByToken();
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<String> updatePassword(UpdatePasswordCommand request) {
        Password oldPassword = new Password(request.getOldPassword());
        Password newPassword = new Password(request.getNewPassword());
        String result = service.updatePassword(oldPassword, newPassword);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<String> getNameById(IdQuery request) {
        Identifier userId = new Identifier(request.getId());
        String result = service.getNameById(userId);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<UserDTO>> getSampleUserByIds(IdsQuery request) {
        List<Identifier> userIds = request.getIds().stream().map(Identifier::new).collect(Collectors.toList());
        List<UserDTO> result = service.getSampleUserByIds(userIds);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    @NoLogin
    public ServiceResult<Boolean> applyUser(ApplyUserCommand request) {
        Boolean result = service.applyUser(request.getUserDTO());
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> passApply(IdCommand request) {
        Boolean result = service.passApply(new Identifier(request.getId()));
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> stopUser(IdCommand request) {
        Boolean result = service.stopUser(new Identifier(request.getId()));
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<UserDTO> getUserByUserName(FindUserByNameQuery request) {
        UserDTO result = service.getUserByUserName(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    protected BaseDoService<UserDTO> getService() {
        return service;
    }
}
