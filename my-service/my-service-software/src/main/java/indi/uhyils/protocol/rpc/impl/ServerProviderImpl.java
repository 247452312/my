package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ServerDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.TestConnByDataRequest;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.ServerProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ServerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 服务器表(Server)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分18秒
 */
@RpcService
public class ServerProviderImpl extends BaseDefaultProvider<ServerDTO> implements ServerProvider {


    @Autowired
    private ServerService service;


    @Override
    protected BaseDoService<ServerDTO> getService() {
        return service;
    }

    @Override
    public Boolean testConnByData(TestConnByDataRequest request) {
        return service.testConnByData(request);
    }

    @Override
    public Boolean testConnById(IdCommand request) {
        return service.testConnById(request);
    }

    @Override
    public List<ServerDTO> getServersIdAndName(DefaultCQE request) {
        return service.getServersIdAndName(request);
    }

    @Override
    public String getNameById(IdQuery request) {
        return service.getNameById(request);
    }
}

