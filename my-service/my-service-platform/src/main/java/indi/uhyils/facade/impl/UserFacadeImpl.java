package indi.uhyils.facade.impl;

import indi.uhyils.annotation.Facade;
import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.response.LoginDTO;
import indi.uhyils.protocol.rpc.UserProvider;
import indi.uhyils.rpc.annotation.RpcReference;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月07日 08时58分
 */
@Facade
public class UserFacadeImpl implements UserFacade {

    @RpcReference
    private UserProvider userProvider;

    @Override
    public LoginDTO forceLogin(LoginCommand loginCommand) {
        ServiceResult<LoginDTO> loginResponse = userProvider.forceLogin(loginCommand);
        return loginResponse.validationAndGet();
    }

}
