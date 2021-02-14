package indi.uhyils.rpc.netty.extension.step.template;

import indi.uhyils.rpc.netty.extension.step.base.RpcConsumerExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcObjectExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcResponseExtension;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月14日 13时01分
 */
public interface ConsumerResponseObjectExtension extends RpcConsumerExtension, RpcResponseExtension, RpcObjectExtension {

    @Override
    default Object doFilter(Object obj, String json) {
        return obj;
    }
}
