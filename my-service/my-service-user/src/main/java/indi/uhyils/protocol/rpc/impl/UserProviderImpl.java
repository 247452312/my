package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.NoLogin;
import indi.uhyils.annotation.Public;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.LoginDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.request.ApplyUserCommand;
import indi.uhyils.pojo.DTO.request.FindUserByNameQuery;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.request.UpdatePasswordCommand;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.BlankCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
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
    public UserDTO getUserById(IdQuery request) {
        Identifier userId = new Identifier(request.getId());
        return service.getUserById(userId);
    }

    @Override
    public String getUserToken(IdQuery request) {
        Identifier userId = new Identifier(request.getId());
        return service.getUserToken(userId);
    }

    @Override
    public TokenInfo getTokenInfoByToken(DefaultCQE request) {
        Token token = new Token(request.getToken());
        return service.getTokenInfoByToken(token);
    }

    @Override
    @Public
    public LoginDTO login(LoginCommand request) {
        UserName username = new UserName(request.getUsername());
        Password password = new Password(request.getPassword());
        return service.login(username, password);
    }

    @Override
    @Public
    public LoginDTO visiterLogin(BlankCommand request) {
        return service.visiterLogin();
    }

    @Override
    public Boolean logout(DefaultCQE request) {
        return service.logout();
    }

    @Override
    public List<UserDTO> getUsers(DefaultCQE request) {
        return service.getUsers();
    }

    @Override
    public UserDTO getUserByToken(DefaultCQE request) {
        return service.getUserByToken();
    }

    @Override
    public String updatePassword(UpdatePasswordCommand request) {
        Password oldPassword = new Password(request.getOldPassword());
        Password newPassword = new Password(request.getNewPassword());
        return service.updatePassword(oldPassword, newPassword);
    }

    @Override
    public String getNameById(IdQuery request) {
        Identifier userId = new Identifier(request.getId());
        return service.getNameById(userId);
    }

    @Override
    public List<UserDTO> getSampleUserByIds(IdsQuery request) {
        List<Identifier> userIds = request.getIds().stream().map(Identifier::new).collect(Collectors.toList());
        return service.getSampleUserByIds(userIds);
    }

    @Override
    @NoLogin
    public Boolean applyUser(ApplyUserCommand request) {
        return service.applyUser(request.getUserDTO());
    }

    @Override
    public Boolean passApply(IdCommand request) {
        return service.passApply(new Identifier(request.getId()));
    }

    @Override
    public Boolean stopUser(IdCommand request) {
        return service.stopUser(new Identifier(request.getId()));
    }

    @Override
    public UserDTO getUserByUserName(FindUserByNameQuery request) {
        return service.getUserByUserName(request);
    }

    @Override
    protected BaseDoService<UserDTO> getService() {
        return service;
    }
}
