package indi.uhyils.rpc.filter.template;

import indi.uhyils.rpc.filter.base.RpcByteFilter;
import indi.uhyils.rpc.filter.base.RpcProviderFilter;
import indi.uhyils.rpc.filter.base.RpcResponseFilter;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时22分
 */
public interface ProviderResponseByteFilter extends RpcProviderFilter, RpcResponseFilter, RpcByteFilter {

    @Override
    default byte[] doFilter(byte[] bytes) {
        return bytes;
    }
}
