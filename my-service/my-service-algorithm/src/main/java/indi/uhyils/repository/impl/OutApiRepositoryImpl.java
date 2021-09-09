package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OutApiAssembler;
import indi.uhyils.dao.OutApiDao;
import indi.uhyils.pojo.DO.OutApiDO;
import indi.uhyils.pojo.DTO.OutApiDTO;
import indi.uhyils.pojo.entity.OutApi;
import indi.uhyils.repository.OutApiRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 开放api(OutApi)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分11秒
 */
@Repository
public class OutApiRepositoryImpl extends AbstractRepository<OutApi, OutApiDO, OutApiDao, OutApiDTO, OutApiAssembler> implements OutApiRepository {

    protected OutApiRepositoryImpl(OutApiAssembler convert, OutApiDao dao) {
        super(convert, dao);
    }


}
