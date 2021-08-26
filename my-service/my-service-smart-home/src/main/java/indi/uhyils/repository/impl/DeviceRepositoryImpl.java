package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.DeviceConvert;
import indi.uhyils.dao.DeviceDao;
import indi.uhyils.pojo.entity.Device;
import indi.uhyils.pojo.DO.DeviceDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.repository.DeviceRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 设备表(Device)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分25秒
 */
@Repository
public class DeviceRepositoryImpl extends AbstractRepository<Device, DeviceDO, DeviceDao> implements DeviceRepository {

    protected DeviceRepositoryImpl(DeviceConvert convert, DeviceDao dao) {
        super(convert, dao);
    }


}
