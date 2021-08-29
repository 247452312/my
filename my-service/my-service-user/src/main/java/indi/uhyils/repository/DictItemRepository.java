package indi.uhyils.repository;

import indi.uhyils.pojo.DO.DictItemDO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetByItemArgsQuery;
import indi.uhyils.pojo.entity.DictId;
import indi.uhyils.pojo.entity.DictItem;
import indi.uhyils.pojo.entity.DictItemCode;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 数据字典子项(DictItem)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分42秒
 */
public interface DictItemRepository extends BaseEntityRepository<DictItemDO, DictItem> {


    /**
     * 根据dictId获取子项
     *
     * @param dictId
     *
     * @return
     */
    List<DictItem> findItemByDictId(DictId dictId);

    /**
     * 查询
     *
     * @param query
     *
     * @return
     */
    Page<DictItem> find(GetByItemArgsQuery query);

    /**
     * 根据itemCode获取所有item
     *
     * @param itemCode
     *
     * @return
     */
    List<DictItem> findItemByDictCode(DictItemCode itemCode);
}
