package indi.uhyils.rpc.netty.extension.step.template;

import indi.uhyils.rpc.netty.extension.step.base.RpcByteExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcProviderExtension;
import indi.uhyils.rpc.netty.extension.step.base.RpcResponseExtension;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时22分
 */
public interface ProviderResponseByteExtension extends RpcProviderExtension, RpcResponseExtension, RpcByteExtension {

    @Override
    default byte[] doFilter(byte[] bytes) {
        return bytes;
    }
}
