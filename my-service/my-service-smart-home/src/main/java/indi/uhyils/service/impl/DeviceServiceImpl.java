package indi.uhyils.service.impl;

import indi.uhyils.assembler.DeviceAssembler;
import indi.uhyils.repository.DeviceRepository;
import indi.uhyils.pojo.DO.DeviceDO;
import indi.uhyils.pojo.DTO.DeviceDTO;
import indi.uhyils.pojo.entity.Device;
import indi.uhyils.service.DeviceService;
import org.springframework.stereotype.Service;

/**
 * 设备表(Device)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分26秒
 */
@Service
public class DeviceServiceImpl extends AbstractDoService<DeviceDO, Device, DeviceDTO, DeviceRepository, DeviceAssembler> implements DeviceService {

    public DeviceServiceImpl(DeviceAssembler assembler, DeviceRepository repository) {
        super(assembler, repository);
    }


}
