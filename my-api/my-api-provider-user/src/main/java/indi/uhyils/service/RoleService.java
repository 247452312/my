package indi.uhyils.service;

import indi.uhyils.pojo.model.RoleEntity;
import indi.uhyils.pojo.request.IdsRequest;
import indi.uhyils.pojo.request.PutDeptsToRoleRequest;
import indi.uhyils.pojo.response.ServiceResult;

/**
 * 角色接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时24分
 */
public interface RoleService extends DefaultEntityService<RoleEntity> {

    /**
     * 给角色添加权限集
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> putDeptsToRole(PutDeptsToRoleRequest request);

    /**
     * 删除 -> 真删. 不是假删
     *
     * @param idsRequest
     * @return
     */
    ServiceResult<Boolean> deleteRoleDept(IdsRequest idsRequest);


}
