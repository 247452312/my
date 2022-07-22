package indi.uhyils.rpc.filter.result;

import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.spi.step.template.ConsumerResponseObjectExtension;

/**
 * 关于serviceResult 返回值客户端处理
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月20日 09时17分
 */
@RpcSpi(name = "serviceResultConsumerResponseExtension")
public class ServiceResultConsumerResponseExtension implements ConsumerResponseObjectExtension {

    @Override
    public Object doFilter(Object obj, RpcData rpcData) {
        if (obj instanceof ServiceResult) {
            final ServiceResult<?> serviceResult = (ServiceResult<?>) obj;
            return serviceResult.validationAndGet();
        }
        return obj;
    }
}
