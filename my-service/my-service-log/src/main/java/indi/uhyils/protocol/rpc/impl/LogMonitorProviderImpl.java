package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.LogMonitorDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.provider.LogMonitorProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.LogMonitorService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JVM日志表(LogMonitor)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@RpcService
public class LogMonitorProviderImpl extends BaseDefaultProvider<LogMonitorDTO> implements LogMonitorProvider {


    @Autowired
    private LogMonitorService service;


    @Override
    protected BaseDoService<LogMonitorDTO> getService() {
        return service;
    }

}

