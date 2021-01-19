package indi.uhyils.rpc.netty.extension.step.template;

import indi.uhyils.rpc.netty.extension.step.base.RpcByteExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcProviderExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcRequestExtension;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时21分
 */
public interface ProviderRequestByteExtension extends RpcProviderExtension, RpcRequestExtension, RpcByteExtension {

    @Override
    default byte[] doFilter(byte[] bytes) {
        return bytes;
    }
}
