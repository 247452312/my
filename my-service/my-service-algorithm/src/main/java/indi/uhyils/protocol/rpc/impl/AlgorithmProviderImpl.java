package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.AlgorithmDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.CellAlgorithmRequest;
import indi.uhyils.pojo.DTO.response.CellAlgorithmResponse;
import indi.uhyils.protocol.rpc.AlgorithmProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.AlgorithmService;
import indi.uhyils.service.BaseDoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 算法表(Algorithm)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分08秒
 */
@RpcService
public class AlgorithmProviderImpl extends BaseDefaultProvider<AlgorithmDTO> implements AlgorithmProvider {


    @Autowired
    private AlgorithmService service;


    @Override
    protected BaseDoService<AlgorithmDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<CellAlgorithmResponse> cellAlgorithm(CellAlgorithmRequest request) {
        CellAlgorithmResponse result = service.cellAlgorithm(request);
        return ServiceResult.buildSuccessResult(result);
    }

}

