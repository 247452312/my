package indi.uhyils.service;


import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.request.PutMenusToDeptsCommand;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import java.util.List;

/**
 * 部门(Dept)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分23秒
 */
public interface DeptService extends BaseDoService<DeptDTO> {


    /**
     * 给权限集添加权限
     *
     * @param deptId   部门id
     * @param powerIds 权限们
     *
     * @return
     */
    Boolean putPowersToDept(Identifier deptId, List<Identifier> powerIds) throws Exception;

    /**
     * 删除 -> 真删. 不是假删
     * 解除权限和权限集的关联关系
     *
     * @param ids 权限ids
     *
     * @return
     */
    Boolean deleteDeptPower(List<Long> ids);


    /**
     * 将许多菜单添加到一个权限集
     *
     * @param request 将许多菜单添加到一个权限集的请求
     *
     * @return 是否成功
     */
    Boolean putMenusToDept(PutMenusToDeptsCommand request);

    /**
     * 获取全部权限集
     *
     * @return 权限集
     */
    List<DeptDTO> getDepts();

    /**
     * 获取所有
     *
     * @param deptId
     *
     * @return
     */
    List<GetAllPowerWithHaveMarkDTO> getAllPowerWithHaveMark(Identifier deptId);


    /**
     * 根据权限集id删除 并且删除关联表
     *
     * @param deptId 权限集id
     *
     * @return 删除是否成功
     */
    Boolean deleteDept(Identifier deptId);
}
