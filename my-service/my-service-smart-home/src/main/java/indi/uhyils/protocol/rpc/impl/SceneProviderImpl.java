package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.SceneDTO;
import indi.uhyils.protocol.rpc.SceneProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 场景表(Scene)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分33秒
 */
@RpcService
public class SceneProviderImpl extends BaseDefaultProvider<SceneDTO> implements SceneProvider {


    @Autowired
    private SceneService service;


    @Override
    protected BaseDoService<SceneDTO> getService() {
        return service;
    }

}

