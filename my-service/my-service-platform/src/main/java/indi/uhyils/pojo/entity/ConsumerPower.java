package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.ConsumerPowerDO;
import indi.uhyils.repository.ConsumerPowerRepository;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.annotation.Default;

/**
 * 消费方权限表(ConsumerPower)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分05秒
 */
public class ConsumerPower extends AbstractDoEntity<ConsumerPowerDO> {

    @Default
    public ConsumerPower(ConsumerPowerDO data) {
        super(data);
    }

    public ConsumerPower(Long id) {
        super(id, new ConsumerPowerDO());
    }

    public ConsumerPower(Long id, ConsumerPowerRepository rep) {
        super(id, new ConsumerPowerDO());
        completion(rep);
    }

    public ConsumerPower(Identifier id) {
        super(id, new ConsumerPowerDO());
    }

}
