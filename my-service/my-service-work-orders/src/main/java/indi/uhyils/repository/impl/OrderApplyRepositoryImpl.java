package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderApplyAssembler;
import indi.uhyils.dao.OrderApplyDao;
import indi.uhyils.pojo.DO.OrderApplyDO;
import indi.uhyils.pojo.DTO.OrderApplyDTO;
import indi.uhyils.pojo.entity.OrderApply;
import indi.uhyils.repository.OrderApplyRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * (OrderApply)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分50秒
 */
@Repository
public class OrderApplyRepositoryImpl extends AbstractRepository<OrderApply, OrderApplyDO, OrderApplyDao, OrderApplyDTO, OrderApplyAssembler> implements OrderApplyRepository {

    protected OrderApplyRepositoryImpl(OrderApplyAssembler convert, OrderApplyDao dao) {
        super(convert, dao);
    }


}
