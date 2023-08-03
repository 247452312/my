package indi.uhyils.mysql.pojo.response.impl;

import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.response.AbstractMysqlResponse;
import indi.uhyils.mysql.util.MysqlUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 预处理ok报文
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 20时04分
 */
public class PerpareOkResponse extends AbstractMysqlResponse {

    private static final byte[] FILL_BYTE = new byte[]{0x00};

    /**
     * sql预编译语句id
     */
    private long sqlId;

    /**
     * 参数数量
     */
    private int paramCount;

    /**
     * 告警计数
     */
    private int warning;

    /**
     * 列
     */
    private List<FieldInfo> fields;

    /**
     * 入参
     */
    private List<FieldInfo> param;

    protected PerpareOkResponse() {
        super();
    }

    @Override
    public byte getFirstByte() {
        return 0x00;
    }

    @Override
    public List<byte[]> toByteNoMarkIndex() {
        return Arrays.asList(new byte[0]);
    }


    /**
     * 获取列信息
     *
     * @return
     */
    private byte[] toFields() {
        List<byte[]> collect = fields.stream().map(FieldInfo::toFieldBytes).collect(Collectors.toList());
        return MysqlUtil.mergeListBytes(collect);
    }

    /**
     * 获取列信息
     *
     * @return
     */
    private byte[] toParams() {
        List<byte[]> collect = param.stream().map(FieldInfo::toFieldBytes).collect(Collectors.toList());
        return MysqlUtil.mergeListBytes(collect);
    }

    /**
     * PREPARE_OK
     *
     * @return
     */
    private byte[] toPrepareOk() {
        List<byte[]> results = new ArrayList<>();
        // sql预编译语句id
        byte[] sqlIdBytes = MysqlUtil.toBytes(sqlId);
        results.add(sqlIdBytes);

        // 列数量
        byte[] colCountBytes = MysqlUtil.toBytes(fields.size());
        results.add(colCountBytes);

        // 参数数量
        byte[] paramCountBytes = MysqlUtil.toBytes(paramCount);
        results.add(paramCountBytes);

        // 填充值
        results.add(FILL_BYTE);

        // 列数量
        byte[] warnCountBytes = MysqlUtil.toBytes(warning);
        results.add(warnCountBytes);

        return MysqlUtil.mergeListBytes(results);


    }

}
