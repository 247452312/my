package indi.uhyils.rpc.spi;


/**
 * rpc的拦截器,可扩展
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 08时04分
 */
public interface RpcSpiExtension {

    /**
     * 默认实现的原型模式的clone方法,子类如果想要实现带有参数的克隆,则重写此方法
     *
     * @return new出来的一个神奇的object
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    default Object rpcClone() throws IllegalAccessException, InstantiationException {
        return this.getClass().newInstance();
    }
}
