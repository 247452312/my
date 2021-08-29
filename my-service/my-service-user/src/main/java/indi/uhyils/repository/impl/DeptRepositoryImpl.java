package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DeptAssembler;
import indi.uhyils.dao.DeptDao;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.DeptMenuDO;
import indi.uhyils.pojo.DO.DeptPowerDO;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.cqe.Arg;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdDTO;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.DeptId;
import indi.uhyils.pojo.entity.MenuId;
import indi.uhyils.pojo.entity.PowerId;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时27分
 */
@Repository
public class DeptRepositoryImpl extends AbstractRepository<Dept, DeptDO, DeptDao, DeptDTO, DeptAssembler> implements DeptRepository {


    protected DeptRepositoryImpl(DeptAssembler assembler, DeptDao dao) {
        super(assembler, dao);
    }

    @Override
    public List<Dept> findByRoleId(Identifier roleId) {
        Arg arg = new Arg("role_id", "=", roleId.getId());
        ArrayList<DeptDO> byArgsNoPage = dao.getByArgsNoPage(Arrays.asList(arg), null);
        return byArgsNoPage.stream().map(Dept::new).collect(Collectors.toList());
    }

    @Override
    public void addPowers(DeptId deptId, PowerId powerId) {
        DeptPowerDO middle = new DeptPowerDO();
        middle.setDeptId(deptId.deptIdValue());
        middle.setPowerId(powerId.powerIdValue());
        middle.preInsert();
        dao.insertDeptPower(middle);
    }

    @Override
    public void cleanPower(DeptId deptId) {
        dao.deleteDeptPowerMiddleByDeptId(deptId.deptIdValue());
    }

    @Override
    public void deleteDeptPower(List<Long> ids) {
        dao.deleteDeptPower(ids);
    }

    @Override
    public void cleanMenu(DeptId deptId) {
        dao.deleteDeptMenuMiddleByDeptId(deptId.deptIdValue());
    }

    @Override
    public void addMenu(DeptId deptId, MenuId menuId) {
        DeptMenuDO t = new DeptMenuDO();
        t.setDeptId(deptId.deptIdValue());
        t.setMenuId(menuId.menuIdValue());
        t.preInsert();
        dao.insertDeptMenu(t);
    }

    @Override
    public List<GetDeptsByMenuIdDTO> findByMenuId(MenuId menuId) {
        return dao.getByMenuId(menuId.menuIdValue());
    }

    @Override
    public List<Dept> findAll() {
        ArrayList<DeptDO> all = dao.getAll();
        return all.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<GetAllPowerWithHaveMarkDTO> getAllPowerWithHaveMark(DeptId deptId) {
        return dao.getAllPowerWithHaveMark(deptId.deptIdValue());
    }


}
