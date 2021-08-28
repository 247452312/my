package indi.uhyils.service.impl;

import indi.uhyils.assembler.DeptAssembler;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.request.PutMenusToDeptsCommand;
import indi.uhyils.pojo.DTO.request.PutPowersToDeptCommand;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.DeptId;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.service.DeptService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * 部门(Dept)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分23秒
 */
@Service
public class DeptServiceImpl extends AbstractDoService<DeptDO, Dept, DeptDTO, DeptRepository, DeptAssembler> implements DeptService {


    public DeptServiceImpl(DeptAssembler assembler, DeptRepository repository) {
        super(assembler, repository);
    }

    @Override
    public ServiceResult<Boolean> putPowersToDept(PutPowersToDeptCommand request) {
        DeptId deptId = new DeptId(request.getDeptId());
        // 清空之前这个部门的权限
        deptId.cleanPower(rep);
        deptId.addPower(request.getPowerIds(), rep);
        return ServiceResult.buildSuccessResult("权限集添加权限成功", Boolean.TRUE);
    }

    @Override
    public ServiceResult<Boolean> deleteDeptPower(IdsCommand request) {
        rep.deleteDeptPower(request.getIds());
        return ServiceResult.buildSuccessResult("删除成功", Boolean.TRUE);
    }

    @Override
    public ServiceResult<Boolean> putMenusToDept(PutMenusToDeptsCommand request) {
        DeptId deptId = new DeptId(request.getDeptId());
        // 清空之前这个部门的按钮
        deptId.cleanMenu(rep);
        deptId.addMenu(request.getMenuIds(), rep);
        return ServiceResult.buildSuccessResult("赋权成功", Boolean.TRUE);
    }

    @Override
    public ServiceResult<List<DeptDTO>> getDepts(DefaultCQE request) {
        List<Dept> depts = rep.findAll();
        List<DeptDTO> deptDTOS = depts.stream().map(assem::toDTO).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("获取成功", deptDTOS);
    }

    @Override
    public ServiceResult<List<GetAllPowerWithHaveMarkDTO>> getAllPowerWithHaveMark(IdQuery request) {
        List<GetAllPowerWithHaveMarkDTO> list = rep.getAllPowerWithHaveMark(new DeptId(request.getId()));
        return ServiceResult.buildSuccessResult("查询权限(包含羁绊)成功", list);
    }

    @Override
    public ServiceResult<Boolean> deleteDept(IdCommand request) {
        Dept dept = rep.find(new Identifier(request.getId()));
        dept.removeMenuLink(rep);
        dept.removePowerLink(rep);
        dept.removeRoleLink(rep);
        return ServiceResult.buildSuccessResult("删除成功", Boolean.TRUE);
    }
}
