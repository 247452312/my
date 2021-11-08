package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.MqInfoDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.MqInfoProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.MqInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * mq连接信息表(MqInfo)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@RpcService
public class MqInfoProviderImpl extends BaseDefaultProvider<MqInfoDTO> implements MqInfoProvider {


    @Autowired
    private MqInfoService service;


    @Override
    protected BaseDoService<MqInfoDTO> getService() {
        return service;
    }

}

