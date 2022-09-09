package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.CompanyDTO;
import indi.uhyils.protocol.rpc.CompanyProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 厂商表(Company)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@RpcService
public class CompanyProviderImpl extends BaseDefaultProvider<CompanyDTO> implements CompanyProvider {


    @Autowired
    private CompanyService service;


    @Override
    protected BaseDoService<CompanyDTO> getService() {
        return service;
    }

}

