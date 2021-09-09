package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.AlgorithmDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.AlgorithmRepository;

/**
 * 算法表(Algorithm)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月09日 20时58分06秒
 */
public class Algorithm extends AbstractDoEntity<AlgorithmDO> {

    public Algorithm(AlgorithmDO dO) {
        super(dO);
    }

    public Algorithm(Long id) {
        super(id, new AlgorithmDO());
    }

    public Algorithm(Long id, AlgorithmRepository rep) {
        super(id, new AlgorithmDO());
        completion(rep);
    }

    public Algorithm(Identifier id) {
        super(id, new AlgorithmDO());
    }

}
