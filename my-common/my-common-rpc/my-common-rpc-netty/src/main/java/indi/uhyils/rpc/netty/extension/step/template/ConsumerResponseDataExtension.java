package indi.uhyils.rpc.netty.extension.step.template;

import indi.uhyils.rpc.netty.extension.step.base.RpcConsumerExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcDataExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcResponseExtension;
import indi.uhyils.rpc.exchange.pojo.RpcData;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时23分
 */
public interface ConsumerResponseDataExtension extends RpcConsumerExtension, RpcResponseExtension, RpcDataExtension {
    @Override
    default RpcData doFilter(RpcData data) {
        return data;
    }
}
