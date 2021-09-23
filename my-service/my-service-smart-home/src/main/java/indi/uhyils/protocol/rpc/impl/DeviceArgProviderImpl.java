package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DeviceArgDTO;
import indi.uhyils.protocol.rpc.DeviceArgProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.DeviceArgService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 设备参数表(DeviceArg)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分18秒
 */
@RpcService
public class DeviceArgProviderImpl extends BaseDefaultProvider<DeviceArgDTO> implements DeviceArgProvider {


    @Autowired
    private DeviceArgService service;


    @Override
    protected BaseDoService<DeviceArgDTO> getService() {
        return service;
    }

}

