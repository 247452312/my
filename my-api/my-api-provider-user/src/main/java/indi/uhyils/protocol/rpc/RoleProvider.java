package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.request.PutDeptsToRoleCommand;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;

/**
 * 角色接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时24分
 */
public interface RoleProvider extends DTOProvider<RoleDTO> {

    /**
     * 根据角色id获取角色
     *
     * @param request 角色id
     *
     * @return 角色
     */
    RoleDTO getRoleByRoleId(IdQuery request);

    /**
     * 给角色添加权限集
     *
     * @param request 角色id 和 权限集们
     *
     * @return 是否成功
     */
    Boolean putDeptsToRole(PutDeptsToRoleCommand request) throws Exception;

    /**
     * 删除 -> 真删. 不是假删
     *
     * @param idsRequest 要删除的中间表id
     *
     * @return 删除是否成功
     */
    Boolean deleteRoleDept(IdsCommand idsRequest);

    /**
     * 获取所有的角色
     *
     * @param request 默认
     *
     * @return 角色
     */
    List<RoleDTO> getRoles(DefaultCQE request);

    /**
     * 获取角色的用户权限集
     *
     * @param request 角色id
     *
     * @return 对应的权限集
     */
    List<DeptDTO> getUserDeptsByRoleId(IdQuery request);

    /**
     * 获取所有权限集(带有角色包不包含此权限集的标记)
     *
     * @param request 角色id
     *
     * @return 所有权限集(带有角色包不包含此权限集的标记)
     */
    List<GetAllDeptWithHaveMarkDTO> getAllDeptWithHaveMark(IdQuery request);

    /**
     * 根据角色id删除角色以及关联表
     *
     * @param request 角色id
     *
     * @return 删除是否成功
     */
    Boolean deleteRole(IdCommand request);


}
