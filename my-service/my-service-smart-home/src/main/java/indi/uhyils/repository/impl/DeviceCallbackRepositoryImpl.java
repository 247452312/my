package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DeviceCallbackAssembler;
import indi.uhyils.dao.DeviceCallbackDao;
import indi.uhyils.pojo.DO.DeviceCallbackDO;
import indi.uhyils.pojo.DTO.DeviceCallbackDTO;
import indi.uhyils.pojo.entity.DeviceCallback;
import indi.uhyils.repository.DeviceCallbackRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 设备回调表(DeviceCallback)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分17秒
 */
@Repository
public class DeviceCallbackRepositoryImpl extends AbstractRepository<DeviceCallback, DeviceCallbackDO, DeviceCallbackDao, DeviceCallbackDTO, DeviceCallbackAssembler> implements DeviceCallbackRepository {

    protected DeviceCallbackRepositoryImpl(DeviceCallbackAssembler convert, DeviceCallbackDao dao) {
        super(convert, dao);
    }


}
