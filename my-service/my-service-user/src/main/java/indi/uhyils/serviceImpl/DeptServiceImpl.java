package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.DeptDao;
import indi.uhyils.dao.MenuDao;
import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.DeptMenuMiddle;
import indi.uhyils.pojo.model.DeptPowerMiddle;
import indi.uhyils.pojo.request.*;
import indi.uhyils.pojo.response.GetAllMenuWithHaveMarkResponse;
import indi.uhyils.pojo.response.GetAllPowerWithHaveMarkResponse;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@Service(group = "${spring.profiles.active}")
public class DeptServiceImpl extends BaseDefaultServiceImpl<DeptEntity> implements DeptService {
    @Autowired
    private DeptDao dao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public ServiceResult<Boolean> putPowersToDept(PutPowersToDeptRequest request) {
        dao.deleteDeptPowerMiddleByDeptId(request.getDeptId());
        String deptId = request.getDeptId();
        for (String powerId : request.getPowerIds()) {
            DeptPowerMiddle middle = DeptPowerMiddle.build(deptId, powerId);
            middle.preInsert();
            dao.insertDeptPower(middle);
        }
        return ServiceResult.buildSuccessResult("权限集添加权限成功", true, request);
    }

    @Override
    public ServiceResult<Boolean> deleteDeptPower(IdsRequest idsRequest) {
        dao.deleteDeptPower(idsRequest.getIds());
        return ServiceResult.buildSuccessResult("删除成功", true, idsRequest);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public ServiceResult<Boolean> putMenusToDept(PutMenusToDeptsRequest request) {
        List<DeptMenuMiddle> build = DeptMenuMiddle.build(request.getDeptId(), request.getMenuIds());
        String deptId = request.getDeptId();
        List<String> deptIds = new ArrayList<>(1);
        deptIds.add(deptId);
        menuDao.deleteDeptMenuByDeptIds(deptIds);
        build.forEach(t -> {
            t.preInsert();
            dao.insertDeptMenu(t);
        });
        return ServiceResult.buildSuccessResult("赋权成功", true, request);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public ServiceResult<Boolean> putDeptsToMenu(PutDeptsToMenuRequest request) {
        String menuId = request.getMenuId();
        List<String> menuIds = new ArrayList<>(1);
        menuIds.add(menuId);
        menuDao.deleteDeptMenuByMenuIds(menuIds);
        List<DeptMenuMiddle> build = DeptMenuMiddle.build(request.getDeptIds(), request.getMenuId());
        build.forEach(t -> {
            t.preInsert();
            dao.insertDeptMenu(t);
        });
        return ServiceResult.buildSuccessResult("赋权成功", true, request);
    }

    @Override
    public ServiceResult<ArrayList<DeptEntity>> getDepts(PutDeptsToMenuRequest request) {
        return ServiceResult.buildSuccessResult("获取成功", dao.getAll(), request);
    }

    @Override
    public ServiceResult<ArrayList<GetAllMenuWithHaveMarkResponse>> getAllMenuWithHaveMark(IdRequest request) {
        ArrayList<GetAllMenuWithHaveMarkResponse> list = menuDao.getAllMenuWithHaveMark(request.getId());
        return ServiceResult.buildSuccessResult("查询菜单(包含羁绊)成功", list, request);
    }

    @Override
    public ServiceResult<ArrayList<GetAllPowerWithHaveMarkResponse>> getAllPowerWithHaveMark(IdRequest request) {
        ArrayList<GetAllPowerWithHaveMarkResponse> list = dao.getAllPowerWithHaveMark(request.getId());
        return ServiceResult.buildSuccessResult("查询权限(包含羁绊)成功", list, request);
    }


    public DeptDao getDao() {
        return dao;
    }

    public void setDao(DeptDao dao) {
        this.dao = dao;
    }
}
