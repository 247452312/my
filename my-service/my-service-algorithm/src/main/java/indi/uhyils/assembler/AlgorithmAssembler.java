package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.AlgorithmDO;
import indi.uhyils.pojo.DTO.AlgorithmDTO;
import indi.uhyils.pojo.entity.Algorithm;

/**
 * 算法表(Algorithm)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分07秒
 */
@Assembler
public class AlgorithmAssembler extends AbstractAssembler<AlgorithmDO, Algorithm, AlgorithmDTO> {

    @Override
    public Algorithm toEntity(AlgorithmDO dO) {
        return new Algorithm(dO);
    }

    @Override
    public Algorithm toEntity(AlgorithmDTO dto) {
        return new Algorithm(toDo(dto));
    }

    @Override
    protected Class<AlgorithmDO> getDoClass() {
        return AlgorithmDO.class;
    }

    @Override
    protected Class<AlgorithmDTO> getDtoClass() {
        return AlgorithmDTO.class;
    }
}

