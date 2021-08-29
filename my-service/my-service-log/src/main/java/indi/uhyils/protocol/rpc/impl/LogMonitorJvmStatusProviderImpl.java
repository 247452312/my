package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.LogMonitorJvmStatusDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.provider.LogMonitorJvmStatusProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.LogMonitorJvmStatusService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JVM状态子表(LogMonitorJvmStatus)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@RpcService
public class LogMonitorJvmStatusProviderImpl extends BaseDefaultProvider<LogMonitorJvmStatusDTO> implements LogMonitorJvmStatusProvider {


    @Autowired
    private LogMonitorJvmStatusService service;


    @Override
    protected BaseDoService<LogMonitorJvmStatusDTO> getService() {
        return service;
    }

}

