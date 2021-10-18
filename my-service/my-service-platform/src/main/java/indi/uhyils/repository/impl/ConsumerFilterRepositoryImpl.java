package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ConsumerFilterAssembler;
import indi.uhyils.dao.ConsumerFilterDao;
import indi.uhyils.pojo.entity.ConsumerFilter;
import indi.uhyils.pojo.DO.ConsumerFilterDO;
import indi.uhyils.pojo.DTO.ConsumerFilterDTO;
import indi.uhyils.repository.ConsumerFilterRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 消费过滤表(ConsumerFilter)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@Repository
public class ConsumerFilterRepositoryImpl extends AbstractRepository<ConsumerFilter, ConsumerFilterDO, ConsumerFilterDao, ConsumerFilterDTO, ConsumerFilterAssembler> implements ConsumerFilterRepository {

    protected ConsumerFilterRepositoryImpl(ConsumerFilterAssembler convert, ConsumerFilterDao dao) {
        super(convert, dao);
    }


}
