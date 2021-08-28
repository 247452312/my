package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.entity.Dept;

/**
 * 部门(Dept)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分21秒
 */
@Assembler
public class DeptAssembler extends AbstractAssembler<DeptDO, Dept, DeptDTO> {

    @Override
    public Dept toEntity(DeptDO dO) {
        return new Dept(dO);
    }

    @Override
    public Dept toEntity(DeptDTO dto) {
        return new Dept(toDo(dto));
    }

    @Override
    protected Class<DeptDO> getDoClass() {
        return DeptDO.class;
    }

    @Override
    protected Class<DeptDTO> getDtoClass() {
        return DeptDTO.class;
    }
}

