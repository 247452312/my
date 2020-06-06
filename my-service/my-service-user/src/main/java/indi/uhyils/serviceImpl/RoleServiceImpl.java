package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.RoleDao;
import indi.uhyils.pojo.model.RoleDeptMiddle;
import indi.uhyils.pojo.model.RoleEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.IdsRequest;
import indi.uhyils.pojo.request.PutDeptsToRoleRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时27分
 */
@Service(group = "${spring.profiles.active}")
public class RoleServiceImpl extends BaseDefaultServiceImpl<RoleEntity> implements RoleService {


    @Autowired
    private RoleDao dao;

    @Override
    public ServiceResult<Boolean> putDeptsToRole(PutDeptsToRoleRequest request) {

        String roleId = request.getRoleId();
        for (String deptId : request.getDeptIds()) {
            RoleDeptMiddle build = RoleDeptMiddle.build(roleId, deptId);
            build.preInsert();
            dao.insertRoleDept(build);
        }
        return ServiceResult.buildSuccessResult("角色添加权限集成功", true, request);
    }

    @Override
    public ServiceResult<Boolean> deleteRoleDept(IdsRequest idsRequest) {
        dao.deleteRoleDept(idsRequest.getIds());
        return ServiceResult.buildSuccessResult("删除成功", true, idsRequest);
    }

    @Override
    public ServiceResult<ArrayList<RoleEntity>> getRoles(DefaultRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", dao.getAll(), request);
    }

    public RoleDao getDao() {
        return dao;
    }

    public void setDao(RoleDao dao) {
        this.dao = dao;
    }
}
