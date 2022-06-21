package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DeviceDTO;
import indi.uhyils.protocol.rpc.DeviceProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 设备表(Device)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分14秒
 */
@RpcService
public class DeviceProviderImpl extends BaseDefaultProvider<DeviceDTO> implements DeviceProvider {


    @Autowired
    private DeviceService service;


    @Override
    protected BaseDoService<DeviceDTO> getService() {
        return service;
    }

}

