package indi.uhyils.service.impl;

import indi.uhyils.assembler.DeviceCallbackAssembler;
import indi.uhyils.repository.DeviceCallbackRepository;
import indi.uhyils.pojo.DO.DeviceCallbackDO;
import indi.uhyils.pojo.DTO.DeviceCallbackDTO;
import indi.uhyils.pojo.entity.DeviceCallback;
import indi.uhyils.service.DeviceCallbackService;
import org.springframework.stereotype.Service;

/**
 * 设备回调表(DeviceCallback)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分18秒
 */
@Service
public class DeviceCallbackServiceImpl extends AbstractDoService<DeviceCallbackDO, DeviceCallback, DeviceCallbackDTO, DeviceCallbackRepository, DeviceCallbackAssembler> implements DeviceCallbackService {

    public DeviceCallbackServiceImpl(DeviceCallbackAssembler assembler, DeviceCallbackRepository repository) {
        super(assembler, repository);
    }


}