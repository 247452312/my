package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.DbInfoAssembler;
import indi.uhyils.repository.DbInfoRepository;
import indi.uhyils.pojo.DO.DbInfoDO;
import indi.uhyils.pojo.DTO.DbInfoDTO;
import indi.uhyils.pojo.entity.DbInfo;
import indi.uhyils.service.DbInfoService;
import org.springframework.stereotype.Service;

/**
 * 数据库连接表(DbInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Service
@ReadWriteMark(tables = {"sys_db_info"})
public class DbInfoServiceImpl extends AbstractDoService<DbInfoDO, DbInfo, DbInfoDTO, DbInfoRepository, DbInfoAssembler> implements DbInfoService {

    public DbInfoServiceImpl(DbInfoAssembler assembler, DbInfoRepository repository) {
        super(assembler, repository);
    }


}
