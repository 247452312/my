package indi.uhyils.rpc.filter.template;

import indi.uhyils.rpc.filter.base.RpcByteFilter;
import indi.uhyils.rpc.filter.base.RpcProviderFilter;
import indi.uhyils.rpc.filter.base.RpcRequestFilter;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时21分
 */
public interface ProviderRequestByteFilter extends RpcProviderFilter, RpcRequestFilter, RpcByteFilter {

    @Override
    default byte[] doFilter(byte[] bytes) {
        return bytes;
    }
}
