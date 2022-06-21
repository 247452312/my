package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.AlgorithmAssembler;
import indi.uhyils.dao.AlgorithmDao;
import indi.uhyils.pojo.DO.AlgorithmDO;
import indi.uhyils.pojo.DTO.AlgorithmDTO;
import indi.uhyils.pojo.entity.Algorithm;
import indi.uhyils.repository.AlgorithmRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 算法表(Algorithm)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分07秒
 */
@Repository
public class AlgorithmRepositoryImpl extends AbstractRepository<Algorithm, AlgorithmDO, AlgorithmDao, AlgorithmDTO, AlgorithmAssembler> implements AlgorithmRepository {

    protected AlgorithmRepositoryImpl(AlgorithmAssembler convert, AlgorithmDao dao) {
        super(convert, dao);
    }


}
