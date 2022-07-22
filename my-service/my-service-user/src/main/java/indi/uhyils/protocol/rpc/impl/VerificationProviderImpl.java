package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.Public;
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
    @Public
    public VerificationGetDTO getVerification(DefaultCQE request) throws IOException {
        return service.getVerification();
    }

    @Override
    @Public
    public Boolean verification(VerificationCommand request) {
        String key = request.getKey();
        String code = request.getCode();
        return service.verification(key, code);
    }
}
