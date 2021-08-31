package indi.uhyils.protocol.rpc.impl;

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
 * @date 文件创建日期 2021年08月31日 19时04分36秒
 */
@RpcService
public class SpaceProviderImpl extends BaseDefaultProvider<SpaceDTO> implements SpaceProvider {


    @Autowired
    private SpaceService service;


    @Override
    protected BaseDoService<SpaceDTO> getService() {
        return service;
    }

}

