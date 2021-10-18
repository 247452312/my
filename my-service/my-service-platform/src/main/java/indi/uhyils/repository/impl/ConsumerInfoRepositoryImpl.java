package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ConsumerInfoAssembler;
import indi.uhyils.dao.ConsumerInfoDao;
import indi.uhyils.pojo.entity.ConsumerInfo;
import indi.uhyils.pojo.DO.ConsumerInfoDO;
import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.repository.ConsumerInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 服务消费方信息表(ConsumerInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@Repository
public class ConsumerInfoRepositoryImpl extends AbstractRepository<ConsumerInfo, ConsumerInfoDO, ConsumerInfoDao, ConsumerInfoDTO, ConsumerInfoAssembler> implements ConsumerInfoRepository {

    protected ConsumerInfoRepositoryImpl(ConsumerInfoAssembler convert, ConsumerInfoDao dao) {
        super(convert, dao);
    }


}
