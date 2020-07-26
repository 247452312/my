package indi.uhyils.service;


import indi.uhyils.pojo.request.CellAlgorithmRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.CellAlgorithmResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.BaseService;

/**
 * 算法接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 17时09分
 */
public interface AlgorithmService extends BaseService {

    /**
     * 调用算法
     *
     * @param request
     * @return
     */
    ServiceResult<CellAlgorithmResponse> cellAlgorithm(CellAlgorithmRequest request);


    /**
     * 获取某个算法的准确率
     *
     * @param request 算法id
     * @return 准确率
     */
    ServiceResult<Double> getAlgorithmAccuracy(IdRequest request);
}
