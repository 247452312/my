package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.ConsumerInfoProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ConsumerInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 服务消费方信息表(ConsumerInfo)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@RpcService
public class ConsumerInfoProviderImpl extends BaseDefaultProvider<ConsumerInfoDTO> implements ConsumerInfoProvider {


    @Autowired
    private ConsumerInfoService service;


    @Override
    protected BaseDoService<ConsumerInfoDTO> getService() {
        return service;
    }

}

