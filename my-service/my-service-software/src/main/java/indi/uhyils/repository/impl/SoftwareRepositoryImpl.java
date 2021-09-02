package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.SoftwareAssembler;
import indi.uhyils.dao.SoftwareDao;
import indi.uhyils.pojo.DO.SoftwareDO;
import indi.uhyils.pojo.DTO.SoftwareDTO;
import indi.uhyils.pojo.entity.Software;
import indi.uhyils.repository.SoftwareRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 中间件表(Software)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分21秒
 */
@Repository
public class SoftwareRepositoryImpl extends AbstractRepository<Software, SoftwareDO, SoftwareDao, SoftwareDTO, SoftwareAssembler> implements SoftwareRepository {

    protected SoftwareRepositoryImpl(SoftwareAssembler convert, SoftwareDao dao) {
        super(convert, dao);
    }


}
