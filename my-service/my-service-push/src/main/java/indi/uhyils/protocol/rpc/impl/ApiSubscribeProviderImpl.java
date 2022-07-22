package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ApiSubscribeDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.SubscribeRequest;
import indi.uhyils.protocol.rpc.ApiSubscribeProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ApiSubscribeService;
import indi.uhyils.service.BaseDoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * api订阅表(ApiSubscribe)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分56秒
 */
@RpcService
public class ApiSubscribeProviderImpl extends BaseDefaultProvider<ApiSubscribeDTO> implements ApiSubscribeProvider {


    @Autowired
    private ApiSubscribeService service;


    @Override
    protected BaseDoService<ApiSubscribeDTO> getService() {
        return service;
    }

    @Override
    public Boolean subscribe(SubscribeRequest request) {
        return service.subscribe(request);
    }
}

