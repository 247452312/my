package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DynamicCodeHistoryAssembler;
import indi.uhyils.dao.DynamicCodeHistoryDao;
import indi.uhyils.pojo.DO.DynamicCodeHistoryDO;
import indi.uhyils.pojo.DTO.DynamicCodeHistoryDTO;
import indi.uhyils.pojo.entity.DynamicCodeHistory;
import indi.uhyils.repository.DynamicCodeHistoryRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 动态代码历史记录表(DynamicCodeHistory)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
@Repository
public class DynamicCodeHistoryRepositoryImpl extends AbstractRepository<DynamicCodeHistory, DynamicCodeHistoryDO, DynamicCodeHistoryDao, DynamicCodeHistoryDTO, DynamicCodeHistoryAssembler> implements DynamicCodeHistoryRepository {

    protected DynamicCodeHistoryRepositoryImpl(DynamicCodeHistoryAssembler convert, DynamicCodeHistoryDao dao) {
        super(convert, dao);
    }


}
