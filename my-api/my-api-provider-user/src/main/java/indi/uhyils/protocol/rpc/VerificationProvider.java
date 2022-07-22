package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.VerificationCommand;
import indi.uhyils.pojo.DTO.response.VerificationGetDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.base.BaseProvider;
import java.io.IOException;

/**
 * 验证码类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月14日 06时28分
 */
public interface VerificationProvider extends BaseProvider {

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
