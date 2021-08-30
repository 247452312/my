package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.DictItemDO;
import indi.uhyils.repository.DictItemRepository;
import java.util.List;

/**
 * 数据字典子项(DictItem)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月27日 08时32分44秒
 */
public class DictItem extends AbstractDoEntity<DictItemDO> {

    public DictItem(DictItemDO dO) {
        super(dO);
    }


    public DictItem(Long id) {
        super(id, new DictItemDO());
    }



}
