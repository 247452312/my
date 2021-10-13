package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.AlgorithmAssembler;
import indi.uhyils.pojo.DO.AlgorithmDO;
import indi.uhyils.pojo.DTO.AlgorithmDTO;
import indi.uhyils.pojo.DTO.request.CellAlgorithmRequest;
import indi.uhyils.pojo.DTO.response.CellAlgorithmResponse;
import indi.uhyils.pojo.entity.Algorithm;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.AlgorithmRepository;
import indi.uhyils.service.AlgorithmService;
import org.springframework.stereotype.Service;

/**
 * 算法表(Algorithm)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分08秒
 */
@Service
@ReadWriteMark(tables = {"sys_algorithm"})
public class AlgorithmServiceImpl extends AbstractDoService<AlgorithmDO, Algorithm, AlgorithmDTO, AlgorithmRepository, AlgorithmAssembler> implements AlgorithmService {

    public AlgorithmServiceImpl(AlgorithmAssembler assembler, AlgorithmRepository repository) {
        super(assembler, repository);
    }


    @Override
    public CellAlgorithmResponse cellAlgorithm(CellAlgorithmRequest request) {
        // TODO 算法暂时告一段落
        return null;
    }

    @Override
    public Double getAlgorithmAccuracy(Identifier alId) {
        Algorithm algorithm = rep.find(alId);
        return algorithm.toData().getAccuracy();
    }
}
