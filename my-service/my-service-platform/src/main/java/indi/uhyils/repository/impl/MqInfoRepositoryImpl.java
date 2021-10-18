package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.MqInfoAssembler;
import indi.uhyils.dao.MqInfoDao;
import indi.uhyils.pojo.entity.MqInfo;
import indi.uhyils.pojo.DO.MqInfoDO;
import indi.uhyils.pojo.DTO.MqInfoDTO;
import indi.uhyils.repository.MqInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * mq连接信息表(MqInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Repository
public class MqInfoRepositoryImpl extends AbstractRepository<MqInfo, MqInfoDO, MqInfoDao, MqInfoDTO, MqInfoAssembler> implements MqInfoRepository {

    protected MqInfoRepositoryImpl(MqInfoAssembler convert, MqInfoDao dao) {
        super(convert, dao);
    }


}
