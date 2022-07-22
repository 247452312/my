package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ApiDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetByArgsAndGroupQuery;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import indi.uhyils.protocol.rpc.ApiProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ApiService;
import indi.uhyils.service.BaseDoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * api表(Api)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分47秒
 */
@RpcService
public class ApiProviderImpl extends BaseDefaultProvider<ApiDTO> implements ApiProvider {


    @Autowired
    private ApiService service;


    @Override
    protected BaseDoService<ApiDTO> getService() {
        return service;
    }

    @Override
    public Page<ApiDTO> getByArgsAndGroup(GetByArgsAndGroupQuery request) {
        Long groupId = request.getGroupId();
        Order order = request.getOrder();
        Limit limit = request.getLimit();
        return service.getByArgsAndGroup(groupId, order, limit);
    }
}

