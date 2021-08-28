package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.pojo.DTO.ResponseDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.ResponseProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 设备指令回应表(Response)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分26秒
 */
@RpcService
@ReadWriteMark(tables = {"sys_response"})
public class ResponseProviderImpl extends BaseDefaultProvider<ResponseDTO> implements ResponseProvider {


    @Autowired
    private ResponseService service;


    @Override
    protected BaseDoService<ResponseDTO> getService() {
        return service;
    }

}

