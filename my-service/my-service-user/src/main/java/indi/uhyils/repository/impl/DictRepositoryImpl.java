package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DictAssembler;
import indi.uhyils.dao.DictDao;
import indi.uhyils.pojo.DO.DictDO;
import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.entity.Dict;
import indi.uhyils.repository.DictRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 数据字典(Dict)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分37秒
 */
@Repository
public class DictRepositoryImpl extends AbstractRepository<Dict, DictDO, DictDao, DictDTO, DictAssembler> implements DictRepository {

    protected DictRepositoryImpl(DictAssembler convert, DictDao dao) {
        super(convert, dao);
    }

}
