package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderLogAssembler;
import indi.uhyils.dao.OrderLogDao;
import indi.uhyils.pojo.DO.OrderLogDO;
import indi.uhyils.pojo.DTO.OrderLogDTO;
import indi.uhyils.pojo.entity.OrderLog;
import indi.uhyils.repository.OrderLogRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 日志表(OrderLog)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分17秒
 */
@Repository
public class OrderLogRepositoryImpl extends AbstractRepository<OrderLog, OrderLogDO, OrderLogDao, OrderLogDTO, OrderLogAssembler> implements OrderLogRepository {

    protected OrderLogRepositoryImpl(OrderLogAssembler convert, OrderLogDao dao) {
        super(convert, dao);
    }


}
