package indi.uhyils.rpc.filter.template;

import indi.uhyils.rpc.filter.base.RpcDataFilter;
import indi.uhyils.rpc.filter.base.RpcProviderFilter;
import indi.uhyils.rpc.filter.base.RpcRequestFilter;
import indi.uhyils.rpc.pojo.RpcData;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时21分
 */
public interface ProviderRequestDataFilter extends RpcProviderFilter, RpcRequestFilter, RpcDataFilter {

    @Override
    default RpcData doFilter(RpcData data) {
        return data;
    }
}
