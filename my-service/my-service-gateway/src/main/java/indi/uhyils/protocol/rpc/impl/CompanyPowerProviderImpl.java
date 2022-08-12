package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.CompanyPowerDTO;
import indi.uhyils.protocol.rpc.CompanyPowerProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.CompanyPowerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 厂商接口调用权限表(CompanyPower)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年08月12日 08时33分
*/
@RpcService
public class CompanyPowerProviderImpl extends BaseDefaultProvider<CompanyPowerDTO> implements CompanyPowerProvider {


    @Autowired
    private CompanyPowerService service;
    
    
    @Override
    protected BaseDoService<CompanyPowerDTO> getService() {
        return service;
    }

}

