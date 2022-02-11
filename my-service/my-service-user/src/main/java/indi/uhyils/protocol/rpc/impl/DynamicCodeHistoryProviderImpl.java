package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DynamicCodeHistoryDTO;
import indi.uhyils.protocol.rpc.DynamicCodeHistoryProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.DynamicCodeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 动态代码历史记录表(DynamicCodeHistory)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
@RpcService
public class DynamicCodeHistoryProviderImpl extends BaseDefaultProvider<DynamicCodeHistoryDTO> implements DynamicCodeHistoryProvider {


    @Autowired
    private DynamicCodeHistoryService service;
    
    
    @Override
    protected BaseDoService<DynamicCodeHistoryDTO> getService() {
        return service;
    }

}

