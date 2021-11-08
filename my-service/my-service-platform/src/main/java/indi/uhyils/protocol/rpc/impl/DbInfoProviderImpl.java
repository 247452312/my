package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DbInfoDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.DbInfoProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.DbInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据库连接表(DbInfo)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@RpcService
public class DbInfoProviderImpl extends BaseDefaultProvider<DbInfoDTO> implements DbInfoProvider {


    @Autowired
    private DbInfoService service;


    @Override
    protected BaseDoService<DbInfoDTO> getService() {
        return service;
    }

}

