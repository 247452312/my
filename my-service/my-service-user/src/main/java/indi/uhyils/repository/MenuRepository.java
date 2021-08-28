package indi.uhyils.repository;

import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkDTO;
import indi.uhyils.pojo.entity.DeptId;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.entity.MenuId;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.MenuIframe;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 权限仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时46分
 */
public interface MenuRepository extends BaseEntityRepository<Menu> {

    /**
     * 清空按钮
     *
     * @param menuId
     */
    void cleanDept(MenuId menuId);

    /**
     * 给按钮添加部门
     *
     * @param menuId
     * @param newDeptId
     */
    void addDept(MenuId menuId, DeptId newDeptId);

    /**
     * 根据iframe获取
     *
     * @param menuIframe
     *
     * @return
     */
    List<Menu> findByIframe(MenuIframe menuIframe);

    /**
     * 根据deptId获取
     *
     * @param identifier
     *
     * @return
     */
    List<Menu> findByDeptId(Identifier identifier);

    /**
     * 查询
     *
     * @param menu
     *
     * @return
     */
    List<GetAllMenuWithHaveMarkDTO> findAllMenuWithHaveMark(Identifier menu);
}
