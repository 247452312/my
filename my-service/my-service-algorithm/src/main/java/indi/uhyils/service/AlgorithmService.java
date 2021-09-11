package indi.uhyils.service;


import indi.uhyils.pojo.DTO.AlgorithmDTO;
import indi.uhyils.pojo.DTO.request.CellAlgorithmRequest;
import indi.uhyils.pojo.DTO.response.CellAlgorithmResponse;
import indi.uhyils.pojo.entity.type.Identifier;

/**
 * 算法表(Algorithm)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分08秒
 */
public interface AlgorithmService extends BaseDoService<AlgorithmDTO> {

    /**
     * 调用算法
     *
     * @param request
     *
     * @return
     */
    CellAlgorithmResponse cellAlgorithm(CellAlgorithmRequest request);


    /**
     * 获取某个算法的准确率
     *
     * @param alId 算法id
     *
     * @return 准确率
     */
    Double getAlgorithmAccuracy(Identifier alId);
}