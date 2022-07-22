package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.request.ExecuteCodeRequest;
import indi.uhyils.pojo.DTO.response.ExecuteCodeResponse;
import indi.uhyils.protocol.rpc.ParsingCodeProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ParsingCodeService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 21时13分
 */
@RpcService
public class ParsingCodeProviderImpl implements ParsingCodeProvider {

    @Autowired
    private ParsingCodeService service;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest request) {
        String result = service.executeCode(request.getClassValue());
        return ExecuteCodeResponse.build(result);
    }
}
