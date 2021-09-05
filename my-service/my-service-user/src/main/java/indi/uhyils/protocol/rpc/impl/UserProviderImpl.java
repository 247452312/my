package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.request.UpdatePasswordCommand;
import indi.uhyils.pojo.DTO.response.LoginDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.protocol.rpc.UserProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
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
public class UserProviderImpl extends BaseDefaultProvider<UserDTO> implements UserProvider {


    @Autowired
    private UserService service;


    @Override
    protected BaseDoService<UserDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<UserDTO> getUserById(IdQuery request) {
        UserDTO result = service.getUserById(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<String> getUserToken(IdQuery request) {
        String result = service.getUserToken(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<TokenInfo> getTokenInfoByToken(DefaultCQE request) {
        TokenInfo result = service.getTokenInfoByToken(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    @NoToken
    public ServiceResult<LoginDTO> login(LoginCommand request) {
        LoginDTO result = service.login(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> logout(DefaultCQE request) {
        Boolean result = service.logout(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<UserDTO>> getUsers(DefaultCQE request) {
        List<UserDTO> result = service.getUsers(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<UserDTO> getUserByToken(DefaultCQE request) {
        UserDTO result = service.getUserByToken(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<String> updatePassword(UpdatePasswordCommand request) {
        String result = service.updatePassword(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<String> getNameById(IdQuery request) {
        String result = service.getNameById(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<UserDTO>> getSampleUserByIds(IdsQuery request) {
        List<UserDTO> result = service.getSampleUserByIds(request);
        return ServiceResult.buildSuccessResult(result);
    }
}
