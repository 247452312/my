package indi.uhyils.repository;

import indi.uhyils.pojo.DO.TraceDetailDO;
import indi.uhyils.pojo.entity.TraceDetail;
import indi.uhyils.pojo.entity.UserSpiderBehavior;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * (TraceDetail)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分54秒
 */
public interface TraceDetailRepository extends BaseEntityRepository<TraceDetailDO, TraceDetail> {


    /**
     * 获取最后n次执行的时间点
     *
     * @param userBehavior
     *
     * @return
     */
    List<Long> findLastTime(UserSpiderBehavior userBehavior);
}
