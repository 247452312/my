package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.DeptAssembler;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.request.PutMenusToDeptsCommand;
import indi.uhyils.pojo.DTO.request.PutPowersToDeptCommand;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.entity.Power;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.service.DeptService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部门(Dept)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分23秒
 */
@Service
@ReadWriteMark(tables = {"sys_dept"})
public class DeptServiceImpl extends AbstractDoService<DeptDO, Dept, DeptDTO, DeptRepository, DeptAssembler> implements DeptService {


    public DeptServiceImpl(DeptAssembler assembler, DeptRepository repository) {
        super(assembler, repository);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_power"})
    public Boolean putPowersToDept(PutPowersToDeptCommand request) {
        Dept deptId = new Dept(request.getDeptId());
        // 清空之前这个部门的权限
        deptId.cleanPower(rep);
        deptId.addPower(request.getPowerIds().stream().map(Power::new).collect(Collectors.toList()), rep);
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_power"})
    public Boolean deleteDeptPower(IdsCommand request) {
        rep.deleteDeptPower(request.getIds());
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu"})
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean putMenusToDept(PutMenusToDeptsCommand request) {
        Dept deptId = new Dept(request.getDeptId());
        // 清空之前这个部门的按钮
        deptId.cleanMenu(rep);
        deptId.addMenu(request.getMenuIds().stream().map(Menu::new).collect(Collectors.toList()), rep);
        return true;
    }

    @Override
    public List<DeptDTO> getDepts(DefaultCQE request) {
        List<Dept> depts = rep.findAll();
        return depts.stream().map(assem::toDTO).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(tables = {"sys_dept_power", "sys_power"})
    public List<GetAllPowerWithHaveMarkDTO> getAllPowerWithHaveMark(IdQuery request) {
        return rep.getAllPowerWithHaveMark(new Dept(request.getId()));
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept", "sys_dept_power", "sys_dept_menu", "sys_role_dept"})
    public Boolean deleteDept(IdCommand request) {
        Dept dept = rep.find(new Identifier(request.getId()));
        dept.removeMenuLink(rep);
        dept.removePowerLink(rep);
        dept.removeRoleLink(rep);
        return true;
    }
}
