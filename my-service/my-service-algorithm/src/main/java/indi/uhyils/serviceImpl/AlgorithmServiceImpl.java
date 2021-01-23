package indi.uhyils.serviceImpl;

import indi.uhyils.dao.AlgorithmDao;
import indi.uhyils.pojo.model.AlgorithmEntity;
import indi.uhyils.pojo.request.CellAlgorithmRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.CellAlgorithmResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.AlgorithmService;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * 算法类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 18时31分
 */
@RpcService
public class AlgorithmServiceImpl implements AlgorithmService {

    @Resource
    AlgorithmDao dao;

    @Override
    public ServiceResult<CellAlgorithmResponse> cellAlgorithm(CellAlgorithmRequest cellAlgorithmRequest) {
        // TODO 算法暂时告一段落
        return null;
    }

    @Override
    public ServiceResult<Double> getAlgorithmAccuracy(IdRequest request) {
        AlgorithmEntity byId = dao.getById(request.getId());
        return ServiceResult.buildSuccessResult("查询成功", byId.getAccuracy(), request);
    }

    public AlgorithmDao getDao() {
        return dao;
    }

    public void setDao(AlgorithmDao dao) {
        this.dao = dao;
    }
}
