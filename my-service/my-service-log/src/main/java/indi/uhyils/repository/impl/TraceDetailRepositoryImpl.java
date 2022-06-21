package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.TraceDetailAssembler;
import indi.uhyils.dao.TraceDetailDao;
import indi.uhyils.pojo.DO.TraceDetailDO;
import indi.uhyils.pojo.DTO.TraceDetailDTO;
import indi.uhyils.pojo.entity.TraceDetail;
import indi.uhyils.pojo.entity.UserSpiderBehavior;
import indi.uhyils.repository.TraceDetailRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.List;


/**
 * (TraceDetail)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分54秒
 */
@Repository
public class TraceDetailRepositoryImpl extends AbstractRepository<TraceDetail, TraceDetailDO, TraceDetailDao, TraceDetailDTO, TraceDetailAssembler> implements TraceDetailRepository {

    protected TraceDetailRepositoryImpl(TraceDetailAssembler convert, TraceDetailDao dao) {
        super(convert, dao);
    }


    @Override
    public List<Long> findLastTime(UserSpiderBehavior userBehavior) {
        return dao.getTimeByIp(userBehavior.ip(), userBehavior.size());
    }
}
