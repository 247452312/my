package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PushPageMsgAssembler;
import indi.uhyils.dao.PushPageMsgDao;
import indi.uhyils.pojo.DO.PushPageMsgDO;
import indi.uhyils.pojo.DTO.PushPageMsgDTO;
import indi.uhyils.pojo.entity.PushPageMsg;
import indi.uhyils.repository.PushPageMsgRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 推送日志信息表(PushPageMsg)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分09秒
 */
@Repository
public class PushPageMsgRepositoryImpl extends AbstractRepository<PushPageMsg, PushPageMsgDO, PushPageMsgDao, PushPageMsgDTO, PushPageMsgAssembler> implements PushPageMsgRepository {

    protected PushPageMsgRepositoryImpl(PushPageMsgAssembler convert, PushPageMsgDao dao) {
        super(convert, dao);
    }


}
