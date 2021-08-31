package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderNodeResultTypeAssembler;
import indi.uhyils.dao.OrderNodeResultTypeDao;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.DTO.OrderNodeResultTypeDTO;
import indi.uhyils.pojo.entity.OrderNodeResultType;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 工单节点处理结果样例表(OrderNodeResultType)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分30秒
 */
@Repository
public class OrderNodeResultTypeRepositoryImpl extends AbstractRepository<OrderNodeResultType, OrderNodeResultTypeDO, OrderNodeResultTypeDao, OrderNodeResultTypeDTO, OrderNodeResultTypeAssembler> implements OrderNodeResultTypeRepository {

    protected OrderNodeResultTypeRepositoryImpl(OrderNodeResultTypeAssembler convert, OrderNodeResultTypeDao dao) {
        super(convert, dao);
    }


}
