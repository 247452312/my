package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.welcome.WelcomeResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.DistributeProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.DistributeService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月10日 08时23分
 */
@RpcService
public class DistributeProviderImpl implements DistributeProvider {

    @Autowired
    private DistributeService service;


    @Override
    public ServiceResult<WelcomeResponse> getWelcomeData(DefaultCQE request) throws Exception {
        WelcomeResponse welcomeData = service.getWelcomeData();
        return ServiceResult.buildSuccessResult(welcomeData);
    }
}
