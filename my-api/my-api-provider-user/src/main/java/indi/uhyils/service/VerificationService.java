package indi.uhyils.service;

import indi.uhyils.pojo.request.VerificationRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.VerificationGetResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.BaseService;

import java.io.IOException;

/**
 * 验证码类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月14日 06时28分
 */
public interface VerificationService extends BaseService {

    /**
     * 获取验证码
     *
     * @param request 默认请求
     * @return 验证码与在redis中的key
     */
    ServiceResult<VerificationGetResponse> getVerification(DefaultRequest request) throws IOException;


    /**
     * 验证码验证
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> verification(VerificationRequest request);

}
