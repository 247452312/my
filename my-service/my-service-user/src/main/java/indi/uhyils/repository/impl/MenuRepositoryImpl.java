package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.MenuAssembler;
import indi.uhyils.dao.DeptDao;
import indi.uhyils.dao.MenuDao;
import indi.uhyils.pojo.DO.DeptMenuDO;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkDTO;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.MenuIframe;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时27分
 */
@Repository
public class MenuRepositoryImpl extends AbstractRepository<Menu, MenuDO, MenuDao, MenuDTO, MenuAssembler> implements MenuRepository {


    @Autowired
    private DeptDao deptDao;

    protected MenuRepositoryImpl(MenuAssembler assembler, MenuDao dao) {
        super(assembler, dao);
    }

    @Override
    public void cleanDept(Menu menuId) {
        dao.deleteDeptMenuByMenuIds(Arrays.asList(menuId.getUnique().getId()));
    }

    @Override
    public void addDept(Menu menuId, Dept newDeptId) {
        DeptMenuDO t = new DeptMenuDO();
        t.setMenuId(menuId.getUnique().getId());
        t.setDeptId(newDeptId.getUnique().getId());
        t.preInsert();
        deptDao.insertDeptMenu(t);
    }

    @Override
    public List<Menu> findByIframe(MenuIframe menuIframe) {
        List<MenuDO> menus = dao.getByIFrame(menuIframe.getIframe());
        return menus.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Menu> findByDeptId(Identifier identifier) {
        List<MenuDO> byDeptIds = dao.getByDeptIds(Collections.singletonList(identifier.getId()));
        return assembler.listToEntity(byDeptIds);
    }

    @Override
    public List<GetAllMenuWithHaveMarkDTO> findAllMenuWithHaveMark(Identifier menu) {
        return dao.getAllMenuWithHaveMark(menu.getId());
    }
}
