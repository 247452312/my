package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.DeptPowerDO;
import indi.uhyils.pojo.DTO.DeptPowerDTO;
import indi.uhyils.pojo.entity.DeptPower;

/**
 * 部门-权限关联(DeptPower)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分30秒
 */
@Assembler
public class DeptPowerAssembler extends AbstractAssembler<DeptPowerDO, DeptPower, DeptPowerDTO> {

    @Override
    public DeptPower toEntity(DeptPowerDO dO) {
        return new DeptPower(dO);
    }

    @Override
    public DeptPower toEntity(DeptPowerDTO dto) {
        return new DeptPower(toDo(dto));
    }

    @Override
    protected Class<DeptPowerDO> getDoClass() {
        return DeptPowerDO.class;
    }

    @Override
    protected Class<DeptPowerDTO> getDtoClass() {
        return DeptPowerDTO.class;
    }
}

