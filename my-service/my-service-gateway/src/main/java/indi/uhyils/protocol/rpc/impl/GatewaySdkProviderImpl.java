package indi.uhyils.protocol.rpc.impl;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.protocol.rpc.GatewaySdkProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.GatewaySdkService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 对外 rpc协议
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 09时06分
 */
@RpcService
public class GatewaySdkProviderImpl implements GatewaySdkProvider {

    @Autowired
    private GatewaySdkService service;

    @Override
    public JSONArray invokeRpc(InvokeCommand command) {
        return service.invokeInterface(command);
    }

}
