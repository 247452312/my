package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.JobDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.JobProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时任务表(Job)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分00秒
 */
@RpcService
public class JobProviderImpl extends BaseDefaultProvider<JobDTO> implements JobProvider {


    @Autowired
    private JobService service;


    @Override
    protected BaseDoService<JobDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<Boolean> pause(IdQuery request) {
        Boolean result = service.pause(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> start(IdQuery request) {
        Boolean result = service.start(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> test(IdQuery request) {
        Boolean result = service.test(request);
        return ServiceResult.buildSuccessResult(result);
    }
}

