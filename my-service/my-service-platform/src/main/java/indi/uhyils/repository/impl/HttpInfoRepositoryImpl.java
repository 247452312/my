package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.HttpInfoAssembler;
import indi.uhyils.dao.HttpInfoDao;
import indi.uhyils.pojo.entity.HttpInfo;
import indi.uhyils.pojo.DO.HttpInfoDO;
import indi.uhyils.pojo.DTO.HttpInfoDTO;
import indi.uhyils.repository.HttpInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * http连接表(HttpInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Repository
public class HttpInfoRepositoryImpl extends AbstractRepository<HttpInfo, HttpInfoDO, HttpInfoDao, HttpInfoDTO, HttpInfoAssembler> implements HttpInfoRepository {

    protected HttpInfoRepositoryImpl(HttpInfoAssembler convert, HttpInfoDao dao) {
        super(convert, dao);
    }


}
