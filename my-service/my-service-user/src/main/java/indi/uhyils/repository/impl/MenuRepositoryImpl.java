package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.MenuConvert;
import indi.uhyils.dao.MenuDao;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时27分
 */
@Repository
public class MenuRepositoryImpl extends AbstractRepository<Menu, MenuDO, MenuDao> implements MenuRepository {


    protected MenuRepositoryImpl(MenuConvert convert, MenuDao dao) {
        super(convert, dao);
    }
}
