package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ConsumerFilterDO;
import indi.uhyils.pojo.entity.ConsumerFilter;
import indi.uhyils.pojo.entity.interfaces.InterfaceInfo;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 消费过滤表(ConsumerFilter)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分03秒
 */
public interface ConsumerFilterRepository extends BaseEntityRepository<ConsumerFilterDO, ConsumerFilter> {


    /**
     * 根据消费者和接口获取规则
     *
     * @param consumerId
     * @param interfaceInfo
     *
     * @return
     */
    List<ConsumerFilter> findByConsumerAndInterface(Long consumerId, InterfaceInfo interfaceInfo);
}