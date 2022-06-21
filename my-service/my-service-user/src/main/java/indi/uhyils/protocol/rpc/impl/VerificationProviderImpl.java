package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.NoLogin;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.VerificationCommand;
import indi.uhyils.pojo.DTO.response.VerificationGetDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.VerificationProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.VerificationService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月14日 06时34分
 */
@RpcService
public class VerificationProviderImpl implements VerificationProvider {


    @Autowired
    private VerificationService service;

    @Override
    @NoLogin
    public ServiceResult<VerificationGetDTO> getVerification(DefaultCQE request) throws IOException {
        VerificationGetDTO result = service.getVerification();
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    @NoLogin
    public ServiceResult<Boolean> verification(VerificationCommand request) {
        String key = request.getKey();
        String code = request.getCode();
        Boolean result = service.verification(key, code);
        return ServiceResult.buildSuccessResult(result);
    }
}
