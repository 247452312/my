package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.DeviceArgAssembler;
import indi.uhyils.repository.DeviceArgRepository;
import indi.uhyils.pojo.DO.DeviceArgDO;
import indi.uhyils.pojo.DTO.DeviceArgDTO;
import indi.uhyils.pojo.entity.DeviceArg;
import indi.uhyils.service.DeviceArgService;
import org.springframework.stereotype.Service;

/**
 * 设备参数表(DeviceArg)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分17秒
 */
@Service
@ReadWriteMark(tables = {"sys_device_arg"})
public class DeviceArgServiceImpl extends AbstractDoService<DeviceArgDO, DeviceArg, DeviceArgDTO, DeviceArgRepository, DeviceArgAssembler> implements DeviceArgService {

    public DeviceArgServiceImpl(DeviceArgAssembler assembler, DeviceArgRepository repository) {
        super(assembler, repository);
    }


}
