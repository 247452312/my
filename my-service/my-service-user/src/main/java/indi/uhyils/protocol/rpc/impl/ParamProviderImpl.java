package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ParamDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.FlushAllSysEvent;
import indi.uhyils.protocol.rpc.ParamProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 系统参数表(Param)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年06月17日 15时53分
 */
@RpcService
public class ParamProviderImpl extends BaseDefaultProvider<ParamDTO> implements ParamProvider {


    @Autowired
    private ParamService service;

    @Override
    public Boolean flushAllSys(FlushAllSysEvent event) {
        return service.flushAllGlobal(event);
    }


    @Override
    protected BaseDoService<ParamDTO> getService() {
        return service;
    }

}

