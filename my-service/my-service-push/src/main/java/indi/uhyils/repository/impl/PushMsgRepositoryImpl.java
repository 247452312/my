package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PushMsgAssembler;
import indi.uhyils.dao.PushMsgDao;
import indi.uhyils.pojo.DO.PushMsgDO;
import indi.uhyils.pojo.DTO.PushMsgDTO;
import indi.uhyils.pojo.entity.PushMsg;
import indi.uhyils.repository.PushMsgRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 推送日志表(PushMsg)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分04秒
 */
@Repository
public class PushMsgRepositoryImpl extends AbstractRepository<PushMsg, PushMsgDO, PushMsgDao, PushMsgDTO, PushMsgAssembler> implements PushMsgRepository {

    protected PushMsgRepositoryImpl(PushMsgAssembler convert, PushMsgDao dao) {
        super(convert, dao);
    }


}
