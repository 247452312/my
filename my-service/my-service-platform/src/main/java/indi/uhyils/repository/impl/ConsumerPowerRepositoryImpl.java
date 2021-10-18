package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ConsumerPowerAssembler;
import indi.uhyils.dao.ConsumerPowerDao;
import indi.uhyils.pojo.entity.ConsumerPower;
import indi.uhyils.pojo.DO.ConsumerPowerDO;
import indi.uhyils.pojo.DTO.ConsumerPowerDTO;
import indi.uhyils.repository.ConsumerPowerRepository;
import indi.uhyils.repository.base.AbstractRepository;


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


}
