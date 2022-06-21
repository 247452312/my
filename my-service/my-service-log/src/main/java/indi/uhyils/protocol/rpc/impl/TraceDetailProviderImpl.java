package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.TraceDetailDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetTraceDetailByHashCodeRequest;
import indi.uhyils.pojo.DTO.response.GetTraceDetailByHashCodeResponse;
import indi.uhyils.protocol.rpc.TraceDetailProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.TraceDetailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (TraceDetail)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分54秒
 */
@RpcService
public class TraceDetailProviderImpl extends BaseDefaultProvider<TraceDetailDTO> implements TraceDetailProvider {


    @Autowired
    private TraceDetailService service;


    @Override
    protected BaseDoService<TraceDetailDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<GetTraceDetailByHashCodeResponse> getTraceDetailByHashCode(GetTraceDetailByHashCodeRequest request) {
        return null;
    }
}

