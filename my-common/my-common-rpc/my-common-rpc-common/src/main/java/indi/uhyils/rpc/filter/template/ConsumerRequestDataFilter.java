package indi.uhyils.rpc.filter.template;

import indi.uhyils.rpc.filter.base.RpcConsumerFilter;
import indi.uhyils.rpc.filter.base.RpcDataFilter;
import indi.uhyils.rpc.filter.base.RpcRequestFilter;
import indi.uhyils.rpc.pojo.RpcData;

/**
 * 消费者,请求,RpcData类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时17分
 */
public interface ConsumerRequestDataFilter extends RpcConsumerFilter, RpcDataFilter, RpcRequestFilter {


    @Override
    default RpcData doFilter(RpcData data) {
        return data;
    }
}
