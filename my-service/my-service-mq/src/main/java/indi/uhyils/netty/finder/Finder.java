package indi.uhyils.netty.finder;

import indi.uhyils.exception.UserException;
import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.rpc.spi.RpcSpiExtension;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

import javax.validation.constraints.NotNull;

/**
 * 协议发现者
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月23日 09时24分
 */
public interface Finder extends RpcSpiExtension {

    /**
     * 方法名称
     */
    String METHOD_NAME = "methodName";
    /**
     * 入参类型
     */
    String PARAMS_TYPE = "paramsType";
    /**
     * 入参
     */
    String DATA = "data";

    /**
     * 检查指定byteBuf开头是否是本协议,注,此处byteBuf只是全部byteBuf的前100位
     *
     * @param byteBuf
     * @return
     */
    @NotNull
    Boolean checkByteBuf(ByteBuf byteBuf);

    /**
     * 从协议体上切割本协议,如果出现半包情况,则请返回null
     *
     * @param byteBuf
     * @return
     */
    ByteBuf cutByteBuf(ByteBuf byteBuf);

    /**
     * 解析byte to model
     *
     * @param ctx
     * @param byteBuf
     * @return
     */
    ProtocolParsingModel parsingByteBuf(ChannelHandlerContext ctx, ByteBuf byteBuf) throws UserException, ClassNotFoundException;

    /**
     * 将返回的obj包装为此协议正确的返回并发送
     *
     * @param ctx
     * @param returnObj
     * @return channel的操作
     */
    ChannelFuture packingByteToRightResponse(ChannelHandlerContext ctx, Object returnObj);


}
