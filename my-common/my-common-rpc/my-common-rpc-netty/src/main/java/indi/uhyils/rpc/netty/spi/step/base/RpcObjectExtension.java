package indi.uhyils.rpc.netty.spi.step.base;


import indi.uhyils.rpc.spi.RpcExtension;

/**
 * rpc类扩展点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月14日 13时00分
 */
public interface RpcObjectExtension extends RpcExtension {

    /**
     * rpc类扩展点
     *
     * @param obj
     * @param json
     * @return
     */
    Object doFilter(Object obj, String json);
}
