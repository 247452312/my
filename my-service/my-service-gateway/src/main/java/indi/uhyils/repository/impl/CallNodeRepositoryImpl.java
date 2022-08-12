package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.CallNodeAssembler;
import indi.uhyils.dao.CallNodeDao;
import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.pojo.DTO.CallNodeDTO;
import indi.uhyils.pojo.entity.CallNode;
import indi.uhyils.repository.CallNodeRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 调用节点表, 真正调用的节点(CallNode)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Repository
public class CallNodeRepositoryImpl extends AbstractRepository<CallNode, CallNodeDO, CallNodeDao, CallNodeDTO, CallNodeAssembler> implements CallNodeRepository {

    protected CallNodeRepositoryImpl(CallNodeAssembler convert, CallNodeDao dao) {
        super(convert, dao);
    }


}
