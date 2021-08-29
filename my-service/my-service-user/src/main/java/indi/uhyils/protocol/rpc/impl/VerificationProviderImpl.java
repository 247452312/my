package indi.uhyils.protocol.rpc.impl;

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
    public ServiceResult<VerificationGetDTO> getVerification(DefaultCQE request) throws IOException {
        VerificationGetDTO result = service.getVerification(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> verification(VerificationCommand request) {
        Boolean result = service.verification(request);
        return ServiceResult.buildSuccessResult(result);
    }
}
