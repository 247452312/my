package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.DTO.PowerDTO;
import indi.uhyils.pojo.entity.Power;

/**
 * 权限(Power)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分50秒
 */
@Assembler
public class PowerAssembler extends AbstractAssembler<PowerDO, Power, PowerDTO> {

    @Override
    public Power toEntity(PowerDO dO) {
        return new Power(dO);
    }

    @Override
    public Power toEntity(PowerDTO dto) {
        return new Power(toDo(dto));
    }

    @Override
    protected Class<PowerDO> getDoClass() {
        return PowerDO.class;
    }

    @Override
    protected Class<PowerDTO> getDtoClass() {
        return PowerDTO.class;
    }
}

