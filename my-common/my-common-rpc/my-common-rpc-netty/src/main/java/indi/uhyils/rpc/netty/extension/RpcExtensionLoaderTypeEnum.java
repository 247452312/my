package indi.uhyils.rpc.netty.extension;

import indi.uhyils.rpc.netty.extension.filter.filter.RpcFilter;
import indi.uhyils.rpc.netty.extension.step.RpcStepExtension;

/**
 * Rpc扩展点的跟路径
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时57分
 */
public enum RpcExtensionLoaderTypeEnum {
    /**
     * RpcFilter
     */
    RPC_FILTER(RpcFilter.class),
    /**
     * RpcStep
     */
    RPC_STEP(RpcStepExtension.class);

    private Class<?> rootClass;

    RpcExtensionLoaderTypeEnum(Class<?> rootClass) {
        this.rootClass = rootClass;
    }

    public Class<?> getRootClass() {
        return rootClass;
    }

    public void setRootClass(Class<?> rootClass) {
        this.rootClass = rootClass;
    }
}
