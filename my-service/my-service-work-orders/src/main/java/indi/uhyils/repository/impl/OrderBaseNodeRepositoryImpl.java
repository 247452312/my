package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderBaseNodeAssembler;
import indi.uhyils.dao.OrderBaseNodeDao;
import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeDTO;
import indi.uhyils.pojo.entity.OrderBaseNode;
import indi.uhyils.repository.OrderBaseNodeRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 工单节点样例表(OrderBaseNode)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分57秒
 */
@Repository
public class OrderBaseNodeRepositoryImpl extends AbstractRepository<OrderBaseNode, OrderBaseNodeDO, OrderBaseNodeDao, OrderBaseNodeDTO, OrderBaseNodeAssembler> implements OrderBaseNodeRepository {

    protected OrderBaseNodeRepositoryImpl(OrderBaseNodeAssembler convert, OrderBaseNodeDao dao) {
        super(convert, dao);
    }


}
