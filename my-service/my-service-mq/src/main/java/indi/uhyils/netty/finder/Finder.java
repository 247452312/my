package indi.uhyils.netty.finder;

import io.netty.buffer.ByteBuf;

/**
 * 协议发现者
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月23日 09时24分
 */
public interface Finder {
    /**
     * 检查指定byteBuf开头是否是本协议,注,此处byteBuf只是全部byteBuf的前100位
     *
     * @param byteBuf
     * @return
     */
    Boolean checkByteBuf(ByteBuf byteBuf);

    /**
     * 从协议体上切割本协议,如果出现半包情况,则请返回null
     *
     * @param byteBuf
     * @return
     */
    ByteBuf cutByteBuf(ByteBuf byteBuf);
}
