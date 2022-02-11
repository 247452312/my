package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DynamicCodeAssembler;
import indi.uhyils.dao.DynamicCodeDao;
import indi.uhyils.pojo.DO.DynamicCodeDO;
import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.entity.DynamicCode;
import indi.uhyils.repository.DynamicCodeRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 动态代码主表(DynamicCode)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
@Repository
public class DynamicCodeRepositoryImpl extends AbstractRepository<DynamicCode, DynamicCodeDO, DynamicCodeDao, DynamicCodeDTO, DynamicCodeAssembler> implements DynamicCodeRepository {

    protected DynamicCodeRepositoryImpl(DynamicCodeAssembler convert, DynamicCodeDao dao) {
        super(convert, dao);
    }


}
