package indi.uhyils.service;

import indi.uhyils.pojo.DTO.request.VerificationCommand;
import indi.uhyils.pojo.DTO.response.VerificationGetDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import java.io.IOException;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时27分
 */
public interface VerificationService extends BaseService {

    /**
     * 获取验证码
     *
     * @param request 默认请求
     *
     * @return 验证码与在redis中的key
     */
    VerificationGetDTO getVerification(DefaultCQE request) throws IOException;


    /**
     * 验证码验证
     *
     * @param request
     *
     * @return
     */
    Boolean verification(VerificationCommand request);
}
