package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.DO.MenuDO;


/**
 * 菜单entity,Do转换
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 18时14分
 */
@Convert
public class MenuConvert extends AbstractDoConvert<Menu, MenuDO> {

    @Override
    public Menu doToEntity(MenuDO dO) {
        return new Menu(dO);
    }
}
