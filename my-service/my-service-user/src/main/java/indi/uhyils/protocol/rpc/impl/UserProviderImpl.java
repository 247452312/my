package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.request.UpdatePasswordCommand;
import indi.uhyils.pojo.DTO.response.LoginResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.provider.UserProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时51分
 */
@RpcService
@ReadWriteMark(tables = {"sys_user"})
public class UserProviderImpl extends BaseDefaultProvider<UserDTO> implements UserProvider {


    @Autowired
    private UserService service;


    @Override
    protected BaseDoService<UserDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<UserDTO> getUserById(IdQuery request) {
        return service.getUserById(request);
    }

    @Override
    public ServiceResult<String> getUserToken(IdQuery request) {
        return service.getUserToken(request);
    }

    @Override
    public ServiceResult<TokenInfo> getTokenInfoByToken(DefaultCQE request) {
        return service.getTokenInfoByToken(request);
    }

    @Override
    public ServiceResult<LoginResponse> login(LoginCommand request) {
        return service.login(request);
    }

    @Override
    public ServiceResult<Boolean> logout(DefaultCQE request) {
        return service.logout(request);
    }

    @Override
    public ServiceResult<List<UserDO>> getUsers(DefaultCQE request) {
        return service.getUsers(request);
    }

    @Override
    public ServiceResult<UserDO> getUserByToken(DefaultCQE request) {
        return service.getUserByToken(request);
    }

    @Override
    public ServiceResult<String> updatePassword(UpdatePasswordCommand request) {
        return service.updatePassword(request);
    }

    @Override
    public ServiceResult<String> getNameById(IdQuery request) {
        return service.getNameById(request);
    }
}
