package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.pojo.DTO.DeviceCallbackDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.DeviceCallbackProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.DeviceCallbackService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 设备回调表(DeviceCallback)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分38秒
 */
@RpcService
@ReadWriteMark(tables = {"sys_device_callback"})
public class DeviceCallbackProviderImpl extends BaseDefaultProvider<DeviceCallbackDTO> implements DeviceCallbackProvider {


    @Autowired
    private DeviceCallbackService service;


    @Override
    protected BaseDoService<DeviceCallbackDTO> getService() {
        return service;
    }

}

