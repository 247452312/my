package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderNodeAssembler;
import indi.uhyils.dao.OrderNodeDao;
import indi.uhyils.enums.OrderNodeResultTypeEnum;
import indi.uhyils.enums.OrderNodeStatusEnum;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DTO.OrderNodeDTO;
import indi.uhyils.pojo.entity.OrderNode;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.Asserts;


/**
 * 工单节点样例表(OrderNode)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分21秒
 */
@Repository
public class OrderNodeRepositoryImpl extends AbstractRepository<OrderNode, OrderNodeDO, OrderNodeDao, OrderNodeDTO, OrderNodeAssembler> implements OrderNodeRepository {

    protected OrderNodeRepositoryImpl(OrderNodeAssembler convert, OrderNodeDao dao) {
        super(convert, dao);
    }


    @Override
    public void makeOrderFault(Long orderNodeId, String msg) {
        dao.makeOrderFault(orderNodeId, OrderNodeStatusEnum.FAULT.getCode(), OrderNodeResultTypeEnum.FAULT.getCode(), msg);
    }

    @Override
    public OrderNode findNext(OrderNode orderNode) {
        OrderNodeDO nextNodeByNodeAndResult = dao.getNextNodeByNodeAndResult(orderNode.getUnique().map(Identifier::getId).orElseThrow(Asserts::throwOptionalException), orderNode.toData().map(OrderNodeDO::getResultId).orElseThrow(Asserts::throwOptionalException));
        return assembler.toEntity(nextNodeByNodeAndResult);
    }

}
