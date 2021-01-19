package indi.uhyils.rpc.netty.extension.step.template;

import indi.uhyils.rpc.netty.extension.step.base.RpcByteExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcConsumerExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcResponseExtension;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时23分
 */
public interface ConsumerResponseByteExtension extends RpcConsumerExtension, RpcResponseExtension, RpcByteExtension {
    @Override
    default byte[] doFilter(byte[] bytes) {
        return bytes;
    }
}
