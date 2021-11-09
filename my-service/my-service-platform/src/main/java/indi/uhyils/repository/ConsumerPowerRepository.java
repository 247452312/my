package indi.uhyils.repository;

import indi.uhyils.enum_.ConsumerStatusEnum;
import indi.uhyils.pojo.DO.ConsumerPowerDO;
import indi.uhyils.pojo.entity.ConsumerPower;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 消费方权限表(ConsumerPower)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分06秒
 */
public interface ConsumerPowerRepository extends BaseEntityRepository<ConsumerPowerDO, ConsumerPower> {


    /**
     * 检查是否可以添加
     *
     * @param consumerPower
     *
     * @return
     */
    Long countPowerByInterfaceAndConsumer(ConsumerPower consumerPower);

    /**
     * 根据消费者id和指定接口获取权限状态
     *
     * @param consumerPower
     *
     * @return
     */
    ConsumerStatusEnum findStatusByConsumerIdAndInterfaceId(ConsumerPower consumerPower);

}