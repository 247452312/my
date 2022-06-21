package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.assembler.MenuAssembler;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.MenuTreeBuilder;
import indi.uhyils.pojo.DTO.response.info.MenuTreeDTO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.MenuIframe;
import indi.uhyils.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 菜单
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时14分
 */
public class Menu extends AbstractDoEntity<MenuDO> {

    private List<Dept> depts;

    @Default
    public Menu(MenuDO data) {
        super(data);
    }

    public Menu(Long id) {
        super(id, new MenuDO());
    }

    public Menu(Identifier id) {
        super(id, new MenuDO());
    }

    public List<Dept> depts() {
        return depts;
    }

    public void removeSelf(MenuRepository rep, MenuAssembler assembler) {
        MenuTreeDTO node = findSelfNode(rep, assembler);
        // 碾平树
        List<MenuDTO> menuDTOS = paveTree(node);
        List<Menu> collect = menuDTOS.stream().map(assembler::toEntity).collect(Collectors.toList());
        rep.remove(collect);
    }

    private List<MenuDTO> paveTree(MenuTreeDTO node) {
        List<MenuDTO> menuDTOS = new ArrayList<>();
        paveTree(node, menuDTOS);
        return menuDTOS;
    }

    private void paveTree(MenuTreeDTO node, List<MenuDTO> list) {
        MenuDTO menuDTO = node.getMenuDTO();
        list.add(menuDTO);
        for (MenuTreeDTO childNode : node.getChild()) {
            paveTree(childNode, list);
        }
    }

    /**
     * 发现本身以及以下的节点树
     *
     * @param rep
     * @param assembler
     *
     * @return
     */
    public MenuTreeDTO findSelfNode(MenuRepository rep, MenuAssembler assembler) {
        MenuDO menuDO = toData();
        Integer iFrame = menuDO.getIFrame();
        List<Menu> menus = rep.findByIframe(new MenuIframe(iFrame));
        MenuTreeBuilder menuTreeBuilder = new MenuTreeBuilder();
        List<MenuDTO> menuDTOS = menus.stream().map(assembler::toDTO).collect(Collectors.toList());
        menuTreeBuilder.addMenu(menuDTOS);
        List<MenuTreeDTO> build = menuTreeBuilder.build();
        return findNode(build, menuDO.getId());
    }

    private MenuTreeDTO findNode(List<MenuTreeDTO> build, Long id) {
        for (MenuTreeDTO menuTreeDTO : build) {
            MenuTreeDTO node = findNode(menuTreeDTO, id);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    private MenuTreeDTO findNode(MenuTreeDTO build, Long id) {
        MenuDTO menuDTO = build.getMenuDTO();
        if (Objects.equals(menuDTO.getId(), id)) {
            return build;
        }
        return findNode(build.getChild(), id);
    }


    public void cleanDept(MenuRepository rep) {
        this.depts = null;
        rep.cleanDept(this);
    }

    public void addDepts(List<Dept> deptIds, MenuRepository rep) {
        List<Dept> newDeptIds = addDeptsToEntity(deptIds);

        for (Dept newDeptId : newDeptIds) {
            rep.addDept(this, newDeptId);
        }
    }

    public List<Dept> addDeptsToEntity(List<Dept> deptIds) {
        if (this.depts == null) {
            this.depts = new ArrayList<>(deptIds.size());
        }
        List<Dept> result = new ArrayList<>();
        for (Dept newDeptId : deptIds) {
            if (this.depts.contains(newDeptId)) {
                continue;
            }
            this.depts.add(newDeptId);
            result.add(newDeptId);
        }
        return result;
    }
}
