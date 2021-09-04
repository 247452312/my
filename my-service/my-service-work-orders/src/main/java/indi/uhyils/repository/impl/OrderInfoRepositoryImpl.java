package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderInfoAssembler;
import indi.uhyils.dao.OrderInfoDao;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.entity.OrderInfo;
import indi.uhyils.repository.OrderInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.List;


/**
 * 工单基础信息样例表(OrderInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分14秒
 */
@Repository
public class OrderInfoRepositoryImpl extends AbstractRepository<OrderInfo, OrderInfoDO, OrderInfoDao, OrderInfoDTO, OrderInfoAssembler> implements OrderInfoRepository {

    protected OrderInfoRepositoryImpl(OrderInfoAssembler convert, OrderInfoDao dao) {
        super(convert, dao);
    }


    @Override
    public List<OrderInfo> findByType(Integer type) {
        List<OrderInfoDO> orderByType = dao.getOrderByType(type);
        return assembler.listToEntity(orderByType);
    }
}
