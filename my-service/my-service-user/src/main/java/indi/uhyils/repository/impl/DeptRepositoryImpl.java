package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DeptAssembler;
import indi.uhyils.dao.DeptDao;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.DeptMenuDO;
import indi.uhyils.pojo.DO.DeptPowerDO;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdDTO;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.entity.Power;
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
    public void addPowers(Dept deptId, Power power) {
        DeptPowerDO middle = new DeptPowerDO();
        middle.setDeptId(deptId.getId().getId());
        middle.setPowerId(power.getId().getId());
        middle.preInsert();
        dao.insertDeptPower(middle);
    }

    @Override
    public void cleanPower(Dept deptId) {
        dao.deleteDeptPowerMiddleByDeptId(deptId.getId().getId());
    }

    @Override
    public void deleteDeptPower(List<Long> ids) {
        dao.deleteDeptPower(ids);
    }

    @Override
    public void cleanMenu(Dept deptId) {
        dao.deleteDeptMenuMiddleByDeptId(deptId.getId().getId());
    }

    @Override
    public void addMenu(Dept deptId, Menu menuId) {
        DeptMenuDO t = new DeptMenuDO();
        t.setDeptId(deptId.getId().getId());
        t.setMenuId(menuId.getId().getId());
        t.preInsert();
        dao.insertDeptMenu(t);
    }

    @Override
    public List<GetDeptsByMenuIdDTO> findByMenuId(Menu menuId) {
        return dao.getByMenuId(menuId.getId().getId());
    }

    @Override
    public List<Dept> findAll() {
        ArrayList<DeptDO> all = dao.getAll();
        return all.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<GetAllPowerWithHaveMarkDTO> getAllPowerWithHaveMark(Dept deptId) {
        return dao.getAllPowerWithHaveMark(deptId.getId().getId());
    }


}
