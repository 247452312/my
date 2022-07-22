package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ApiGroupDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.ApiGroupProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ApiGroupService;
import indi.uhyils.service.BaseDoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * api组表(ApiGroup)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分52秒
 */
@RpcService
public class ApiGroupProviderImpl extends BaseDefaultProvider<ApiGroupDTO> implements ApiGroupProvider {


    @Autowired
    private ApiGroupService service;


    @Override
    protected BaseDoService<ApiGroupDTO> getService() {
        return service;
    }

    @Override
    public String test(IdQuery request) {
        return service.test(request);
    }

    @Override
    public List<ApiGroupDTO> getCanBeSubscribed(DefaultCQE request) {
        return service.getCanBeSubscribed(request);
    }
}

