package indi.uhyils.protocol.rpc.provider;


import indi.uhyils.pojo.DTO.request.CellAlgorithmRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.response.CellAlgorithmResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.BaseProvider;

/**
 * 算法接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 17时09分
 */
public interface AlgorithmProvider extends BaseProvider {

    /**
     * 调用算法
     *
     * @param request
     *
     * @return
     */
    ServiceResult<CellAlgorithmResponse> cellAlgorithm(CellAlgorithmRequest request);


    /**
     * 获取某个算法的准确率
     *
     * @param request 算法id
     *
     * @return 准确率
     */
    ServiceResult<Double> getAlgorithmAccuracy(IdRequest request);
}
