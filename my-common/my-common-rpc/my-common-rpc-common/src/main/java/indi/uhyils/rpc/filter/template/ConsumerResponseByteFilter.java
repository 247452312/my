package indi.uhyils.rpc.filter.template;

import indi.uhyils.rpc.filter.base.RpcByteFilter;
import indi.uhyils.rpc.filter.base.RpcConsumerFilter;
import indi.uhyils.rpc.filter.base.RpcResponseFilter;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时23分
 */
public interface ConsumerResponseByteFilter extends RpcConsumerFilter, RpcResponseFilter, RpcByteFilter {
    @Override
    default byte[] doFilter(byte[] bytes) {
        return bytes;
    }
}
