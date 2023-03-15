package indi.uhyils.rpc.netty.factory;

import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 09时20分
 */
public class NettyInitDtoFactory {

    private NettyInitDtoFactory() {
    }

    /**
     * 创建基础nettyInitDto
     *
     * @return
     */
    public static NettyInitDto createNettyInitDto(String host, Integer port, Integer weight, RpcCallBack callBack) {
        return NettyInitDto.build(port, host, callBack, weight);


    }

    /**
     * 创建调用自身的nettyInitDto
     *
     * @return
     */
    public static NettyInitDto createSelfNettyInitDto() {
        return NettyInitDto.buildSelf();
    }
}
