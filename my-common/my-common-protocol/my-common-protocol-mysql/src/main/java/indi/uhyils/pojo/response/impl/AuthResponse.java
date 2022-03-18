package indi.uhyils.pojo.response.impl;

import indi.uhyils.MysqlUtil;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.pojo.response.AbstractMysqlResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    private static final String VERSION = "5.7.36";

    /**
     * 协议结尾
     */
    private static final String END_OF_PROTO = "mysql_native_password";


    private static final AtomicLong RANDOM_NUM = new AtomicLong(0);

    public AuthResponse(Long mysqlHandler) {
        super(mysqlHandler);
    }

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
    public List<byte[]> toByteNoMarkIndex() {
        List<byte[]> results = new ArrayList<>();

        results.add(toServerVersionInfo());

        results.add(toServerThreadId());

        byte[] e = toAuthPluginName();
        byte[] randomHigh = new byte[8];
        System.arraycopy(e, 0, randomHigh, 0, 8);
        results.add(randomHigh);

        results.add(new byte[1]);

        byte[] clientPower = toCapabilityFlag();
        byte[] clientPowerLow = new byte[2];
        System.arraycopy(clientPower, 2, clientPowerLow, 0, 2);
        results.add(clientPowerLow);

        results.add(new byte[]{toCharset()});

        results.add(toMysqlStatus());

        byte[] clientPowerHigh = new byte[2];
        System.arraycopy(clientPower, 0, clientPowerHigh, 0, 2);
        results.add(clientPowerHigh);
        results.add(MysqlUtil.toBytes(e.length, 1));
        results.add(new byte[10]);
        byte[] randomLow = new byte[e.length - 8];
        System.arraycopy(e, 8, randomLow, 0, e.length - 8);
        results.add(randomLow);
        results.add(END_OF_PROTO.getBytes(StandardCharsets.UTF_8));
        results.add(new byte[1]);
        return Arrays.asList(MysqlUtil.mergeListBytes(results));
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
        return MysqlUtil.toBytes(0x00000001L, 4);
    }


    /**
     * 字符编码集
     *
     * @return
     */
    private byte toCharset() {
        return (byte) 0x2D;
    }

    /**
     * 服务器状态
     *
     * @return
     */
    private byte[] toMysqlStatus() {
        MysqlServerStatusEnum serverStatusNoBackslashEscapes = MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES;
        int code = serverStatusNoBackslashEscapes.getCode();
        return MysqlUtil.toBytes(code, 2);
    }

    /**
     * 获取服务器权能标志
     *
     * @return
     */
    private byte[] toCapabilityFlag() {
        /*List<ClientPowerEnum> clientPowerEnums = Arrays.asList(
            ClientPowerEnum.CLIENT_LONG_PASSWORD,
            ClientPowerEnum.CLIENT_FOUND_ROWS,
            ClientPowerEnum.CLIENT_LONG_FLAG,
            ClientPowerEnum.CLIENT_CONNECT_WITH_DB,
            ClientPowerEnum.CLIENT_NO_SCHEMA,
            ClientPowerEnum.CLIENT_COMPRESS,
            ClientPowerEnum.CLIENT_ODBC,
            ClientPowerEnum.CLIENT_LOCAL_FILES,
            ClientPowerEnum.CLIENT_IGNORE_SPACE,
            ClientPowerEnum.CLIENT_PROTOCOL_41,
            ClientPowerEnum.CLIENT_INTERACTIVE,
            ClientPowerEnum.CLIENT_SSL,
            ClientPowerEnum.CLIENT_IGNORE_SIGPIPE,
            ClientPowerEnum.CLIENT_TRANSACTIONS,
            ClientPowerEnum.CLIENT_RESERVED,
            ClientPowerEnum.CLIENT_SECURE_CONNECTION,
            ClientPowerEnum.CLIENT_MULTI_STATEMENTS,
            ClientPowerEnum.CLIENT_MULTI_RESULTS

        );
        int result = 0;
        for (ClientPowerEnum clientPowerEnum : clientPowerEnums) {
            result |= clientPowerEnum.getCode();
        }
        return MysqlUtil.toBytes(result, 4);*/
        return new byte[]{(byte) 0xFF, (byte) 0xC7, (byte) 0xFF, (byte) 0xFF};
    }

    /**
     * 挑战随机数
     *
     * @return
     */
    private byte[] toAuthPluginName() {
        // 20位随机数
        byte[] randomBytes = RandomUtils.nextBytes(20);
        getMysqlHandler().setPassword(randomBytes);

        List<byte[]> result = new ArrayList<>();
        result.add(randomBytes);
        result.add(new byte[]{0x00});
//        result.add(END_OF_PROTO.getBytes(StandardCharsets.UTF_8));
//        result.add(new byte[]{0x00});
        return MysqlUtil.mergeListBytes(result);
    }
}
