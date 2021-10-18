package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ConsumerFilterDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.ConsumerFilterProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ConsumerFilterService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消费过滤表(ConsumerFilter)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@RpcService
public class ConsumerFilterProviderImpl extends BaseDefaultProvider<ConsumerFilterDTO> implements ConsumerFilterProvider {


    @Autowired
    private ConsumerFilterService service;


    @Override
    protected BaseDoService<ConsumerFilterDTO> getService() {
        return service;
    }

}

