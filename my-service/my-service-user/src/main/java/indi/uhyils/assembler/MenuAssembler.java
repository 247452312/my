package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.entity.Menu;

/**
 * 菜单(Menu)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分45秒
 */
@Assembler
public class MenuAssembler extends AbstractAssembler<MenuDO, Menu, MenuDTO> {

    @Override
    public Menu toEntity(MenuDO dO) {
        return new Menu(dO);
    }

    @Override
    public Menu toEntity(MenuDTO dto) {
        return new Menu(toDo(dto));
    }

    @Override
    protected Class<MenuDO> getDoClass() {
        return MenuDO.class;
    }

    @Override
    protected Class<MenuDTO> getDtoClass() {
        return MenuDTO.class;
    }
}

