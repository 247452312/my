package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ConsumerPowerAssembler;
import indi.uhyils.dao.ConsumerPowerDao;
import indi.uhyils.enum_.ConsumerStatusEnum;
import indi.uhyils.pojo.DO.ConsumerPowerDO;
import indi.uhyils.pojo.DTO.ConsumerPowerDTO;
import indi.uhyils.pojo.entity.ConsumerPower;
import indi.uhyils.repository.ConsumerPowerRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.Asserts;


/**
 * 消费方权限表(ConsumerPower)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分06秒
 */
@Repository
public class ConsumerPowerRepositoryImpl extends AbstractRepository<ConsumerPower, ConsumerPowerDO, ConsumerPowerDao, ConsumerPowerDTO, ConsumerPowerAssembler> implements ConsumerPowerRepository {

    protected ConsumerPowerRepositoryImpl(ConsumerPowerAssembler convert, ConsumerPowerDao dao) {
        super(convert, dao);
    }


    @Override
    public Long countPowerByInterfaceAndConsumer(ConsumerPower consumerPower) {
        ConsumerPowerDO consumerPowerDO = consumerPower.toData();
        Asserts.assertTrue(consumerPowerDO != null);
        LambdaQueryWrapper<ConsumerPowerDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ConsumerPowerDO::getInterfaceId, consumerPowerDO.getInterfaceId());
        queryWrapper.eq(ConsumerPowerDO::getConsumerId, consumerPowerDO.getConsumerId());
        return dao.selectCount(queryWrapper);
    }

    @Override
    public ConsumerStatusEnum findStatusByConsumerIdAndInterfaceId(ConsumerPower consumerPower) {
        ConsumerPowerDO consumerPowerDO = consumerPower.toData();
        Asserts.assertTrue(consumerPowerDO != null);
        LambdaQueryWrapper<ConsumerPowerDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ConsumerPowerDO::getConsumerId, consumerPowerDO.getConsumerId());
        queryWrapper.eq(ConsumerPowerDO::getInterfaceId, consumerPowerDO.getInterfaceId());
        ConsumerPowerDO resultDO = dao.selectOne(queryWrapper);
        Asserts.assertTrue(resultDO != null, "不存在指定权限申请");
        return ConsumerStatusEnum.parse(resultDO.getStatus());
    }
}
