package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DbInfoAssembler;
import indi.uhyils.dao.DbInfoDao;
import indi.uhyils.pojo.entity.DbInfo;
import indi.uhyils.pojo.DO.DbInfoDO;
import indi.uhyils.pojo.DTO.DbInfoDTO;
import indi.uhyils.repository.DbInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 数据库连接表(DbInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分07秒
 */
@Repository
public class DbInfoRepositoryImpl extends AbstractRepository<DbInfo, DbInfoDO, DbInfoDao, DbInfoDTO, DbInfoAssembler> implements DbInfoRepository {

    protected DbInfoRepositoryImpl(DbInfoAssembler convert, DbInfoDao dao) {
        super(convert, dao);
    }


}
