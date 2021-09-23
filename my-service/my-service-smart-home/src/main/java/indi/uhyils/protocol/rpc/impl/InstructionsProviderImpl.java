package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.InstructionsDTO;
import indi.uhyils.protocol.rpc.InstructionsProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.InstructionsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 说明书表(Instructions)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分26秒
 */
@RpcService
public class InstructionsProviderImpl extends BaseDefaultProvider<InstructionsDTO> implements InstructionsProvider {


    @Autowired
    private InstructionsService service;


    @Override
    protected BaseDoService<InstructionsDTO> getService() {
        return service;
    }

}

