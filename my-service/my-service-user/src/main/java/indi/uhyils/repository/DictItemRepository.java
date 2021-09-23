package indi.uhyils.repository;

import indi.uhyils.pojo.DO.DictItemDO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import indi.uhyils.pojo.entity.Dict;
import indi.uhyils.pojo.entity.DictItem;
import indi.uhyils.pojo.entity.type.Identifier;
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
    List<DictItem> findItemByDictId(Dict dictId);

    /**
     * 根据itemCode获取所有item
     *
     * @param itemCode
     *
     * @return
     */
    List<DictItem> findItemByDictCode(String itemCode);

    /**
     * 根据条件查询
     *
     * @param dictId
     * @param args
     * @param order
     * @param limit
     *
     * @return
     */
    Page<DictItem> find(Identifier dictId, List<Arg> args, Order order, Limit limit);

}
