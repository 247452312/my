package indi.uhyils.rpc.filter.result;

import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exchange.pojo.content.RpcRequestContent;
import indi.uhyils.rpc.netty.spi.step.template.ProviderResponseObjExtension;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月20日 10时08分
 */
@RpcSpi(name = "myProviderResponseObjExtension")
public class MyProviderResponseObjExtension implements ProviderResponseObjExtension {

    @Override
    public Object doFilter(Object obj, RpcRequestContent requestContent) {
        if (obj instanceof ServiceResult) {
            return obj;
        }
        return ServiceResult.buildSuccessResult(obj);
    }
}
