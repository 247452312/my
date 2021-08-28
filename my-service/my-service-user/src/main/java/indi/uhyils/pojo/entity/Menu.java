package indi.uhyils.pojo.entity;

import indi.uhyils.assembler.MenuAssembler;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.MenuTreeBuilder;
import indi.uhyils.pojo.DTO.response.info.MenuTreeDTO;
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

    public Menu(MenuDO menuDO) {
        super(menuDO);
    }

    public void removeSelf(MenuRepository rep, MenuAssembler assembler) {
        MenuTreeDTO node = findSelfNode(rep, assembler);
        // 碾平为树
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
        MenuDO menuDO = toDo();
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
}
