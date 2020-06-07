package indi.uhyils.service;

import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.RoleEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.IdsRequest;
import indi.uhyils.pojo.request.PutDeptsToRoleRequest;
import indi.uhyils.pojo.response.GetAllDeptWithHaveMarkResponse;
import indi.uhyils.pojo.response.ServiceResult;

import java.util.ArrayList;

/**
 * 角色接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时24分
 */
public interface RoleService extends DefaultEntityService<RoleEntity> {

    /**
     * 根据角色id获取角色
     *
     * @param request 角色id
     * @return 角色
     */
    ServiceResult<RoleEntity> getRoleByRoleIdNoToken(IdRequest request);

    /**
     * 给角色添加权限集
     *
     * @param request 角色id 和 权限集们
     * @return 是否成功
     */
    ServiceResult<Boolean> putDeptsToRole(PutDeptsToRoleRequest request);

    /**
     * 删除 -> 真删. 不是假删
     *
     * @param idsRequest 要删除的中间表id
     * @return 删除是否成功
     */
    ServiceResult<Boolean> deleteRoleDept(IdsRequest idsRequest);

    /**
     * 获取所有的角色
     *
     * @param request 默认
     * @return 角色
     */
    ServiceResult<ArrayList<RoleEntity>> getRoles(DefaultRequest request);

    /**
     * 获取角色的用户权限集
     *
     * @param request 角色id
     * @return 对应的权限集
     */
    ServiceResult<ArrayList<DeptEntity>> getUserDeptsByRoleId(IdRequest request);

    /**
     * 获取所有权限集(带有角色包不包含此权限集的标记)
     *
     * @param request 角色id
     * @return 所有权限集(带有角色包不包含此权限集的标记)
     */
    ServiceResult<ArrayList<GetAllDeptWithHaveMarkResponse>> getAllDeptWithHaveMark(IdRequest request);

    /**
     * 根据角色id删除角色以及关联表
     *
     * @param request 角色id
     * @return 删除是否成功
     */
    ServiceResult<Boolean> deleteRole(IdRequest request);


}
