package indi.uhyils.rpc.netty.extension.step.template;

import indi.uhyils.rpc.netty.extension.step.base.RpcDataExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcProviderExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcRequestExtension;
import indi.uhyils.rpc.exchange.pojo.RpcData;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时21分
 */
public interface ProviderRequestDataExtension extends RpcProviderExtension, RpcRequestExtension, RpcDataExtension {

    @Override
    default RpcData doFilter(RpcData data) {
        return data;
    }
}
