package indi.uhyils.rpc.filter.template;

import indi.uhyils.rpc.filter.base.RpcByteFilter;
import indi.uhyils.rpc.filter.base.RpcConsumerFilter;
import indi.uhyils.rpc.filter.base.RpcRequestFilter;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时18分
 */
public interface ConsumerRequestByteFilter extends RpcConsumerFilter, RpcByteFilter, RpcRequestFilter {

    @Override
    default byte[] doFilter(byte[] bytes) {
        return bytes;
    }
}
