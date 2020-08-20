package indi.uhyils.filter;

import com.alibaba.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * dubbo连接器, 此处的拦截器调用了dubbo熔断器配置类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月19日 15时43分
 */
@Activate(group = Constants.CONSUMER)
public class HystrixFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 熔断器配置
        DubboHystrixCommand command = new DubboHystrixCommand(invoker, invocation);
        return command.execute();
    }
}
