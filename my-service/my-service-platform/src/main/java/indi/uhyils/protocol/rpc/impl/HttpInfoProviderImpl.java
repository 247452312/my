package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.HttpInfoDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.HttpInfoProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.HttpInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * http连接表(HttpInfo)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@RpcService
public class HttpInfoProviderImpl extends BaseDefaultProvider<HttpInfoDTO> implements HttpInfoProvider {


    @Autowired
    private HttpInfoService service;


    @Override
    protected BaseDoService<HttpInfoDTO> getService() {
        return service;
    }

}

