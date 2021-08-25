package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.RoleDao;
import indi.uhyils.dao.UserDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.RoleDeptMiddle;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DTO.request.PutDeptsToRoleRequest;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.request.base.IdsRequest;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.protocol.rpc.provider.RoleProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import java.util.ArrayList;
import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时27分
 */
@RpcService
@ReadWriteMark(tables = {"sys_role"})
public class RoleProviderImpl extends BaseDefaultProvider<RoleDO> implements RoleProvider {


    @Resource
    private RoleDao dao;

    @Resource
    private UserDao userDao;

    @Override
    @NoToken
    public ServiceResult<RoleDO> getRoleByRoleId(IdRequest request) {
        RoleDO byId = dao.getById(request.getId());
        if (byId == null) {
            return ServiceResult.buildFailedResult("查询失败", null);
        }
        return ServiceResult.buildSuccessResult("获取用户角色成功", byId);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept", "sys_role_dept"})
    public ServiceResult<Boolean> putDeptsToRole(PutDeptsToRoleRequest request) throws Exception {

        Long roleId = request.getRoleId();
        dao.deleteRoleDeptMiddleByRoleId(roleId);
        for (Long deptId : request.getDeptIds()) {
            RoleDeptMiddle build = RoleDeptMiddle.build(roleId, deptId);
            build.preInsert(request);
            dao.insertRoleDept(build);
        }
        return ServiceResult.buildSuccessResult("角色添加权限集成功", Boolean.TRUE);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept"})
    public ServiceResult<Boolean> deleteRoleDept(IdsRequest idsRequest) {
        dao.deleteRoleDept(idsRequest.getIds());
        return ServiceResult.buildSuccessResult("删除成功", Boolean.TRUE);
    }

    @Override
    public ServiceResult<ArrayList<RoleDO>> getRoles(DefaultRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", dao.getAll());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dept", "sys_role_dept"})
    public ServiceResult<ArrayList<DeptDO>> getUserDeptsByRoleId(IdRequest request) {
        return ServiceResult.buildSuccessResult("查询角色成功", userDao.getUserDeptsByRoleId(request.getId()));
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_role_dept", "sys_dept"})
    public ServiceResult<ArrayList<GetAllDeptWithHaveMarkResponse>> getAllDeptWithHaveMark(IdRequest request) {
        ArrayList<GetAllDeptWithHaveMarkResponse> allDeptWithHaveMark = dao.getAllDeptWithHaveMark(request.getId());
        return ServiceResult.buildSuccessResult("查询带存在标记的权限集成功", allDeptWithHaveMark);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept", "sys_user"})
    public ServiceResult<Boolean> deleteRole(IdRequest request) {
        RoleDO t = getDao().getById(request.getId());
        if (t == null) {
            return ServiceResult.buildFailedResult("查询失败", null);
        }
        t.setDeleteFlag(Boolean.TRUE);
        t.preUpdate(request);
        dao.update(t);
        dao.deleteRoleDeptMiddleByRoleId(request.getId());
        dao.updateUserRoleToNullByRoleId(request.getId());

        return ServiceResult.buildSuccessResult("删除成功", Boolean.TRUE);

    }


    @Override
    public RoleDao getDao() {
        return dao;
    }

    public void setDao(RoleDao dao) {
        this.dao = dao;
    }
}
