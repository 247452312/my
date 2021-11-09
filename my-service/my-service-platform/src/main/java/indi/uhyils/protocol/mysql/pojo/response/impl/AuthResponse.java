package indi.uhyils.protocol.mysql.pojo.response.impl;

import indi.uhyils.protocol.mysql.context.MysqlContext;
import indi.uhyils.protocol.mysql.enums.ClientPowerEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.response.AbstractMysqlResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import indi.uhyils.util.MathUtil;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.lang3.RandomUtils;


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
    private static final String VERSION = "8.0.20";

    /**
     * 协议结尾
     */
    private static final String END_OF_PROTO = "caching_sha2_password";


    private static final AtomicLong RANDOM_NUM = new AtomicLong(0);

    protected AuthResponse(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    /**
     * 认证报文为协议版本号,此处协议版本为10
     *
     * @return
     */
    @Override

    public byte getFirstByte() {
        return 0x4A;
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
        ClientPowerEnum[] values = ClientPowerEnum.values();
        int result = 0;
        for (ClientPowerEnum clientPowerEnum : values) {
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
        return 0x2D;
    }

    /**
     * 服务器状态
     *
     * @return
     */
    private byte[] toMysqlStatus() {
        MysqlServerStatusEnum serverStatusNoBackslashEscapes = MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES;
        int code = serverStatusNoBackslashEscapes.getCode();
        return MysqlUtil.toBytes(code);
    }

    /**
     * 获取服务器权能标志
     *
     * @return
     */
    private byte[] toCapabilityFlag() {
        List<ClientPowerEnum> clientPowerEnums = Arrays.asList(
            ClientPowerEnum.CLIENT_LONG_PASSWORD,
            ClientPowerEnum.CLIENT_FOUND_ROWS,
            ClientPowerEnum.CLIENT_LONG_FLAG,
            ClientPowerEnum.CLIENT_ODBC,
            ClientPowerEnum.CLIENT_LOCAL_FILES,
            ClientPowerEnum.CLIENT_IGNORE_SPACE,
            ClientPowerEnum.CLIENT_PROTOCOL_41,
            ClientPowerEnum.CLIENT_INTERACTIVE,
            ClientPowerEnum.CLIENT_SSL,
            ClientPowerEnum.CLIENT_IGNORE_SIGPIPE,
            ClientPowerEnum.CLIENT_TRANSACTIONS,
            ClientPowerEnum.CLIENT_RESERVED,
            ClientPowerEnum.CLIENT_SECURE_CONNECTION
        );
        int result = 0;
        for (ClientPowerEnum clientPowerEnum : clientPowerEnums) {
            result |= clientPowerEnum.getCode();
        }
        return MysqlUtil.toBytes(result);
    }

    /**
     * 挑战随机数 + 结尾
     *
     * @return
     */
    private byte[] toAuthPluginName() {
        MysqlHandler mysqlHandler = getMysqlHandler();
        // 密码
        byte[] serverPassword = MysqlContext.SERVER_PASSWORD.getBytes(StandardCharsets.UTF_8);
        // 20位随机数
        byte[] randomBytes = RandomUtils.nextBytes(20);
        // 加密一次的密码
        byte[] encodeOnePassword = MathUtil.shaEncode(serverPassword);
        // 加密的20位随机数
        byte[] encodeRandom = MathUtil.shaEncode(randomBytes);
        // 加密两次的密码
        byte[] encodeTwoPassword = MathUtil.shaEncode(encodeOnePassword);
        // 加密一次的密码和加密的20位随机数做异或操作
        byte[] xorPasswordAndRandom = MathUtil.xor(encodeOnePassword, encodeRandom);

        byte[] result = new byte[xorPasswordAndRandom.length + encodeTwoPassword.length];
        System.arraycopy(xorPasswordAndRandom, 0, result, 0, xorPasswordAndRandom.length);
        System.arraycopy(encodeTwoPassword, 0, result, xorPasswordAndRandom.length, encodeTwoPassword.length);
        mysqlHandler.setPassword(result);
        return encodeRandom;
    }
}
