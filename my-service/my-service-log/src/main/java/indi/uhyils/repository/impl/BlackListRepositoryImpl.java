package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.BlackListAssembler;
import indi.uhyils.dao.BlackListDao;
import indi.uhyils.pojo.DO.BlackListDO;
import indi.uhyils.pojo.DTO.BlackListDTO;
import indi.uhyils.pojo.entity.BlackList;
import indi.uhyils.repository.BlackListRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 黑名单(BlackList)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分52秒
 */
@Repository
public class BlackListRepositoryImpl extends AbstractRepository<BlackList, BlackListDO, BlackListDao, BlackListDTO, BlackListAssembler> implements BlackListRepository {

    protected BlackListRepositoryImpl(BlackListAssembler convert, BlackListDao dao) {
        super(convert, dao);
    }


}
