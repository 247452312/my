package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.RelegationDO;
import indi.uhyils.pojo.DTO.RelegationDTO;
import indi.uhyils.pojo.entity.Relegation;

/**
 * 接口降级策略(Relegation)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分23秒
 */
@Assembler
public class RelegationAssembler extends AbstractAssembler<RelegationDO, Relegation, RelegationDTO> {

    @Override
    public Relegation toEntity(RelegationDO dO) {
        return new Relegation(dO);
    }

    @Override
    public Relegation toEntity(RelegationDTO dto) {
        return new Relegation(toDo(dto));
    }

    @Override
    protected Class<RelegationDO> getDoClass() {
        return RelegationDO.class;
    }

    @Override
    protected Class<RelegationDTO> getDtoClass() {
        return RelegationDTO.class;
    }
}

