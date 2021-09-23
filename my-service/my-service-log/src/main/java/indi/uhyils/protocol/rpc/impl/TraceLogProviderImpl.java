package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.TraceLogDTO;
import indi.uhyils.protocol.rpc.TraceLogProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.TraceLogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (TraceLog)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
@RpcService
public class TraceLogProviderImpl extends BaseDefaultProvider<TraceLogDTO> implements TraceLogProvider {


    @Autowired
    private TraceLogService service;


    @Override
    protected BaseDoService<TraceLogDTO> getService() {
        return service;
    }

}

