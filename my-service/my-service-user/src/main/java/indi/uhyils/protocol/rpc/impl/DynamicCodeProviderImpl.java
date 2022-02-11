package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.protocol.rpc.DynamicCodeProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.DynamicCodeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 动态代码主表(DynamicCode)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
@RpcService
public class DynamicCodeProviderImpl extends BaseDefaultProvider<DynamicCodeDTO> implements DynamicCodeProvider {


    @Autowired
    private DynamicCodeService service;
    
    
    @Override
    protected BaseDoService<DynamicCodeDTO> getService() {
        return service;
    }

}

