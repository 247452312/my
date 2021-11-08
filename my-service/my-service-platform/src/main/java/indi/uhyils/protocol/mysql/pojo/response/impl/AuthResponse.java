package indi.uhyils.protocol.mysql.pojo.response.impl;

import indi.uhyils.protocol.mysql.enums.ClientPowerEnum;
import indi.uhyils.protocol.mysql.pojo.response.AbstractMysqlResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 服务端认证报文
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月08日 09时00分
 */
public class AuthResponse extends AbstractMysqlResponse {

    /**
     * mysql版本
     */
    private static final String VERSION = "8.0.23";

    private static final AtomicLong RANDOM_NUM = new AtomicLong(0);

    /**
     * 认证报文为协议版本号,此处协议版本为10
     *
     * @return
     */
    @Override

    public byte getFirstByte() {
        return 0x0A;
    }

    @Override
    public byte[] toByteNoMarkIndex() {
        return new byte[0];
    }

    /**
     * 服务器版本信息
     *
     * @return
     */
    private byte[] toServerVersionInfo() {
        byte[] bytes = VERSION.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        result[bytes.length] = 0x00;
        return result;
    }

    /**
     * 服务器线程id, 不暴露
     *
     * @return
     */
    private byte[] toServerThreadId() {
        return MysqlUtil.toBytes(-1);
    }

    /**
     * 获取挑战随机数
     *
     * @return
     */
    private byte[] toRandomNum() {
        return MysqlUtil.toBytes(RANDOM_NUM.getAndIncrement());
    }

    /**
     * 客户端权限
     *
     * @return
     */
    private byte[] toClientPower() {
        List<ClientPowerEnum> clientPowerEnums = Arrays.asList(
            ClientPowerEnum.CLIENT_FOUND_ROWS,
            ClientPowerEnum.CLIENT_CONNECT_WITH_DB,
            ClientPowerEnum.CLIENT_PROTOCOL_41,
            ClientPowerEnum.CLIENT_INTERACTIVE,
            ClientPowerEnum.CLIENT_SECURE_CONNECTION,
            ClientPowerEnum.CLIENT_MULTI_STATEMENTS,
            ClientPowerEnum.CLIENT_MULTI_RESULTS,
            ClientPowerEnum.CLIENT_LONG_FLAG
        );
        int result = 0;
        for (ClientPowerEnum clientPowerEnum : clientPowerEnums) {
            result |= clientPowerEnum.getCode();
        }
        return MysqlUtil.toBytes(result);
    }

    /**
     * 字符编码集
     *
     * @return
     */
    private byte toCharset() {
        return 33;
    }
}
