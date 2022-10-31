package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.protocol.rpc.WorldOfWarcraftProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.WorldOfWarcraftService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月26日 09时30分
 */
@RpcService
public class WorldOfWarcraftProviderImpl implements WorldOfWarcraftProvider {

    @Autowired
    private WorldOfWarcraftService service;

}
