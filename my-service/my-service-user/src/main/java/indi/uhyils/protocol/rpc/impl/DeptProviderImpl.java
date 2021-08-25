package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.DeptDao;
import indi.uhyils.dao.MenuDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.DeptMenuMiddle;
import indi.uhyils.pojo.DO.DeptPowerMiddle;
import indi.uhyils.pojo.DTO.request.PutDeptsToMenuRequest;
import indi.uhyils.pojo.DTO.request.PutMenusToDeptsRequest;
import indi.uhyils.pojo.DTO.request.PutPowersToDeptRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.request.base.IdsRequest;
import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkResponse;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.protocol.rpc.provider.DeptProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
@ReadWriteMark(tables = {"sys_dept"})
public class DeptProviderImpl extends BaseDefaultProvider<DeptDO> implements DeptProvider {

    @Resource
    private DeptDao dao;

    @Resource
    private MenuDao menuDao;

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_power"})
    public ServiceResult<Boolean> putPowersToDept(PutPowersToDeptRequest request) throws Exception {
        dao.deleteDeptPowerMiddleByDeptId(request.getDeptId());
        Long deptId = request.getDeptId();
        for (Long powerId : request.getPowerIds()) {
            DeptPowerMiddle middle = DeptPowerMiddle.build(deptId, powerId);
            middle.preInsert(request);
            dao.insertDeptPower(middle);
        }
        return ServiceResult.buildSuccessResult("权限集添加权限成功", Boolean.TRUE);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_power"})
    public ServiceResult<Boolean> deleteDeptPower(IdsRequest idsRequest) {
        dao.deleteDeptPower(idsRequest.getIds());
        return ServiceResult.buildSuccessResult("删除成功", Boolean.TRUE);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu"})
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public ServiceResult<Boolean> putMenusToDept(PutMenusToDeptsRequest request) {
        List<DeptMenuMiddle> build = DeptMenuMiddle.build(request.getDeptId(), request.getMenuIds());
        Long deptId = request.getDeptId();
        List<Long> deptIds = new ArrayList<>(1);
        deptIds.add(deptId);
        menuDao.deleteDeptMenuByDeptIds(deptIds);
        build.forEach(t -> {
            try {
                t.preInsert(request);
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
            dao.insertDeptMenu(t);
        });
        return ServiceResult.buildSuccessResult("赋权成功", Boolean.TRUE);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_menu"})
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public ServiceResult<Boolean> putDeptsToMenu(PutDeptsToMenuRequest request) {
        Long menuId = request.getMenuId();
        List<Long> menuIds = new ArrayList<>(1);
        menuIds.add(menuId);
        menuDao.deleteDeptMenuByMenuIds(menuIds);
        List<DeptMenuMiddle> build = DeptMenuMiddle.build(request.getDeptIds(), request.getMenuId());
        build.forEach(t -> {
            try {
                t.preInsert(request);
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
            dao.insertDeptMenu(t);
        });
        return ServiceResult.buildSuccessResult("赋权成功", Boolean.TRUE);
    }

    @Override
    public ServiceResult<ArrayList<DeptDO>> getDepts(PutDeptsToMenuRequest request) {
        return ServiceResult.buildSuccessResult("获取成功", dao.getAll());
    }

    @Override
    @ReadWriteMark(tables = {"sys_dept_menu", "sys_menu"})
    public ServiceResult<ArrayList<GetAllMenuWithHaveMarkResponse>> getAllMenuWithHaveMark(IdRequest request) {
        ArrayList<GetAllMenuWithHaveMarkResponse> list = menuDao.getAllMenuWithHaveMark(request.getId());
        return ServiceResult.buildSuccessResult("查询菜单(包含羁绊)成功", list);
    }

    @Override
    @ReadWriteMark(tables = {"sys_dept_power", "sys_power"})
    public ServiceResult<ArrayList<GetAllPowerWithHaveMarkResponse>> getAllPowerWithHaveMark(IdRequest request) {
        ArrayList<GetAllPowerWithHaveMarkResponse> list = dao.getAllPowerWithHaveMark(request.getId());
        return ServiceResult.buildSuccessResult("查询权限(包含羁绊)成功", list);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept", "sys_dept_power", "sys_dept_menu", "sys_role_dept"})
    public ServiceResult<Boolean> deleteDept(IdRequest request) {
        DeptDO t = getDao().getById(request.getId());
        if (t == null) {
            return ServiceResult.buildFailedResult("查无此人", null);
        }
        t.setDeleteFlag(Boolean.TRUE);
        t.preUpdate(request);
        getDao().update(t);
        dao.deleteDeptPowerMiddleByDeptId(request.getId());
        dao.deleteDeptMenuMiddleByDeptId(request.getId());
        dao.deleteRoleDeptMiddleByDeptId(request.getId());

        return ServiceResult.buildSuccessResult("删除成功", Boolean.TRUE);
    }

    @Override
    public DeptDao getDao() {
        return dao;
    }

    public void setDao(DeptDao dao) {
        this.dao = dao;
    }
}



