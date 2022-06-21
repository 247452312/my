package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderNodeFieldValueAssembler;
import indi.uhyils.dao.OrderNodeFieldValueDao;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.pojo.DTO.OrderNodeFieldValueDTO;
import indi.uhyils.pojo.entity.OrderNodeFieldValue;
import indi.uhyils.repository.OrderNodeFieldValueRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分27秒
 */
@Repository
public class OrderNodeFieldValueRepositoryImpl extends AbstractRepository<OrderNodeFieldValue, OrderNodeFieldValueDO, OrderNodeFieldValueDao, OrderNodeFieldValueDTO, OrderNodeFieldValueAssembler> implements OrderNodeFieldValueRepository {

    protected OrderNodeFieldValueRepositoryImpl(OrderNodeFieldValueAssembler convert, OrderNodeFieldValueDao dao) {
        super(convert, dao);
    }


}
