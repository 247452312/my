package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ProviderInfoAssembler;
import indi.uhyils.dao.ProviderInfoDao;
import indi.uhyils.pojo.entity.ProviderInfo;
import indi.uhyils.pojo.DO.ProviderInfoDO;
import indi.uhyils.pojo.DTO.ProviderInfoDTO;
import indi.uhyils.repository.ProviderInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 服务提供者表(ProviderInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@Repository
public class ProviderInfoRepositoryImpl extends AbstractRepository<ProviderInfo, ProviderInfoDO, ProviderInfoDao, ProviderInfoDTO, ProviderInfoAssembler> implements ProviderInfoRepository {

    protected ProviderInfoRepositoryImpl(ProviderInfoAssembler convert, ProviderInfoDao dao) {
        super(convert, dao);
    }


}
