package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DeviceArgAssembler;
import indi.uhyils.dao.DeviceArgDao;
import indi.uhyils.pojo.DO.DeviceArgDO;
import indi.uhyils.pojo.entity.DeviceArg;
import indi.uhyils.repository.DeviceArgRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 设备参数表(DeviceArg)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分13秒
 */
@Repository
public class DeviceArgRepositoryImpl extends AbstractRepository<DeviceArg, DeviceArgDO, DeviceArgDao> implements DeviceArgRepository {

    protected DeviceArgRepositoryImpl(DeviceArgAssembler convert, DeviceArgDao dao) {
        super(convert, dao);
    }


}
