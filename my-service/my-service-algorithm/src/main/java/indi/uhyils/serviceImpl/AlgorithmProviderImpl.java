package indi.uhyils.serviceImpl;

import indi.uhyils.dao.AlgorithmDao;
import indi.uhyils.pojo.DO.AlgorithmDO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.CellAlgorithmRequest;
import indi.uhyils.pojo.DTO.response.CellAlgorithmResponse;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.provider.AlgorithmProvider;
import indi.uhyils.rpc.annotation.RpcService;
import javax.annotation.Resource;

/**
 * 算法类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 18时31分
 */
@RpcService
public class AlgorithmProviderImpl implements AlgorithmProvider {

    @Resource
    AlgorithmDao dao;

    @Override
    public ServiceResult<CellAlgorithmResponse> cellAlgorithm(CellAlgorithmRequest cellAlgorithmRequest) {
        // TODO 算法暂时告一段落
        return null;
    }

    @Override
    public ServiceResult<Double> getAlgorithmAccuracy(IdQuery request) {
        AlgorithmDO byId = dao.getById(request.getId());
        return ServiceResult.buildSuccessResult("查询成功", byId.getAccuracy());
    }

    public AlgorithmDao getDao() {
        return dao;
    }

    public void setDao(AlgorithmDao dao) {
        this.dao = dao;
    }
}
