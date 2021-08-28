package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.pojo.DTO.DeviceDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.DeviceProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 设备表(Device)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分10秒
 */
@RpcService
@ReadWriteMark(tables = {"sys_device"})
public class DeviceProviderImpl extends BaseDefaultProvider<DeviceDTO> implements DeviceProvider {


    @Autowired
    private DeviceService service;


    @Override
    protected BaseDoService<DeviceDTO> getService() {
        return service;
    }

}

