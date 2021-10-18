package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PlatformPowerAssembler;
import indi.uhyils.dao.PlatformPowerDao;
import indi.uhyils.pojo.entity.PlatformPower;
import indi.uhyils.pojo.DO.PlatformPowerDO;
import indi.uhyils.pojo.DTO.PlatformPowerDTO;
import indi.uhyils.repository.PlatformPowerRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 接口权限表(PlatformPower)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@Repository
public class PlatformPowerRepositoryImpl extends AbstractRepository<PlatformPower, PlatformPowerDO, PlatformPowerDao, PlatformPowerDTO, PlatformPowerAssembler> implements PlatformPowerRepository {

    protected PlatformPowerRepositoryImpl(PlatformPowerAssembler convert, PlatformPowerDao dao) {
        super(convert, dao);
    }


}
