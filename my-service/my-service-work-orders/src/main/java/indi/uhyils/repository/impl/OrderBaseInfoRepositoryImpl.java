package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderBaseInfoAssembler;
import indi.uhyils.dao.OrderBaseInfoDao;
import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.entity.OrderBaseInfo;
import indi.uhyils.repository.OrderBaseInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 工单基础信息样例表(OrderBaseInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分54秒
 */
@Repository
public class OrderBaseInfoRepositoryImpl extends AbstractRepository<OrderBaseInfo, OrderBaseInfoDO, OrderBaseInfoDao, OrderBaseInfoDTO, OrderBaseInfoAssembler> implements OrderBaseInfoRepository {

    protected OrderBaseInfoRepositoryImpl(OrderBaseInfoAssembler convert, OrderBaseInfoDao dao) {
        super(convert, dao);
    }


}
