package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderBaseNodeResultTypeAssembler;
import indi.uhyils.dao.OrderBaseNodeResultTypeDao;
import indi.uhyils.pojo.DO.OrderBaseNodeResultTypeDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeResultTypeDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeResultType;
import indi.uhyils.repository.OrderBaseNodeResultTypeRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 工单节点处理结果样例表(OrderBaseNodeResultType)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分04秒
 */
@Repository
public class OrderBaseNodeResultTypeRepositoryImpl extends AbstractRepository<OrderBaseNodeResultType, OrderBaseNodeResultTypeDO, OrderBaseNodeResultTypeDao, OrderBaseNodeResultTypeDTO, OrderBaseNodeResultTypeAssembler> implements OrderBaseNodeResultTypeRepository {

    protected OrderBaseNodeResultTypeRepositoryImpl(OrderBaseNodeResultTypeAssembler convert, OrderBaseNodeResultTypeDao dao) {
        super(convert, dao);
    }


}
