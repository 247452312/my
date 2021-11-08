package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PlatformRoleDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.PlatformRoleProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PlatformRoleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色表(PlatformRole)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@RpcService
public class PlatformRoleProviderImpl extends BaseDefaultProvider<PlatformRoleDTO> implements PlatformRoleProvider {


    @Autowired
    private PlatformRoleService service;


    @Override
    protected BaseDoService<PlatformRoleDTO> getService() {
        return service;
    }

}

