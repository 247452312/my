package indi.uhyils.rpc.filter.base;

import indi.uhyils.rpc.pojo.RpcData;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时07分
 */
public interface RpcDataFilter extends RpcFilter {

    /**
     * 拦截
     *
     * @param data
     * @return
     */
    RpcData doFilter(RpcData data);
}
