package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.pojo.DTO.SpaceDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.SpaceProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 空间坐标表(Space)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时51分00秒
 */
@RpcService
@ReadWriteMark(tables = {"sys_space"})
public class SpaceProviderImpl extends BaseDefaultProvider<SpaceDTO> implements SpaceProvider {


    @Autowired
    private SpaceService service;


    @Override
    protected BaseDoService<SpaceDTO> getService() {
        return service;
    }

}

