package indi.uhyils.repository;

import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdDTO;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.entity.Power;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 角色仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时46分
 */
public interface DeptRepository extends BaseEntityRepository<DeptDO, Dept> {

    /**
     * 根据角色id获取全部dept
     *
     * @param roleId
     *
     * @return
     */
    List<Dept> findByRoleId(Identifier roleId);

    /**
     * 添加新power
     *
     * @param dept
     * @param power
     */
    void addPowers(Dept dept, Power power);

    /**
     * 清空权限
     *
     * @param deptId
     */
    void cleanPower(Dept deptId);

    /**
     * 删除deptPower
     *
     * @param ids
     */
    void deleteDeptPower(List<Long> ids);

    /**
     * 清空按钮
     *
     * @param deptId
     */
    void cleanMenu(Dept deptId);

    /**
     * 添加新按钮
     *
     * @param deptId
     * @param newPowerId
     */
    void addMenu(Dept deptId, Menu newPowerId);

    /**
     * 根据按钮id查询
     *
     * @param menuId
     *
     * @return
     */
    List<GetDeptsByMenuIdDTO> findByMenuId(Menu menuId);

    /**
     * 获取全部部门
     *
     * @return
     */
    List<Dept> findAll();

    /**
     * 根据haveMark获取Power
     *
     * @param deptId
     *
     * @return
     */
    List<GetAllPowerWithHaveMarkDTO> getAllPowerWithHaveMark(Dept deptId);
}
