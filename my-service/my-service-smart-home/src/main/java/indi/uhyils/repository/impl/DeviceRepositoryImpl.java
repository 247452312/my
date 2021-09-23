package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DeviceAssembler;
import indi.uhyils.dao.DeviceDao;
import indi.uhyils.pojo.DO.DeviceDO;
import indi.uhyils.pojo.DTO.DeviceDTO;
import indi.uhyils.pojo.entity.Device;
import indi.uhyils.repository.DeviceRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 设备表(Device)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分13秒
 */
@Repository
public class DeviceRepositoryImpl extends AbstractRepository<Device, DeviceDO, DeviceDao, DeviceDTO, DeviceAssembler> implements DeviceRepository {

    protected DeviceRepositoryImpl(DeviceAssembler convert, DeviceDao dao) {
        super(convert, dao);
    }


}
