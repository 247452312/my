package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OutApiDTO;
import indi.uhyils.protocol.rpc.OutApiProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OutApiService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 开放api(OutApi)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分12秒
 */
@RpcService
public class OutApiProviderImpl extends BaseDefaultProvider<OutApiDTO> implements OutApiProvider {


    @Autowired
    private OutApiService service;


    @Override
    protected BaseDoService<OutApiDTO> getService() {
        return service;
    }

}

