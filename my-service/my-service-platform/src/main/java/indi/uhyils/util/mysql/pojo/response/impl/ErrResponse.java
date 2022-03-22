package indi.uhyils.util.mysql.pojo.response.impl;

import indi.uhyils.enums.MysqlErrCodeEnum;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.history.MysqlUtil;
import indi.uhyils.util.mysql.pojo.response.AbstractMysqlResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 20时39分
 */
public class ErrResponse extends AbstractMysqlResponse {

    /**
     * mysql错误返回代码
     */
    private MysqlErrCodeEnum errCode;

    /**
     * mysql服务器状态
     */
    private MysqlServerStatusEnum status;

    /**
     * 服务器消息
     */
    private String msg;

    public ErrResponse(MysqlTcpInfo mysqlTcpInfo, MysqlErrCodeEnum errCode, MysqlServerStatusEnum status, String msg) {
        super(mysqlTcpInfo.index());
        this.errCode = errCode;
        this.status = status;
        this.msg = msg;
    }

    public ErrResponse(MysqlTcpInfo mysqlTcpInfo, MysqlErrCodeEnum errCode, MysqlServerStatusEnum status) {
        super(mysqlTcpInfo.index());
        this.errCode = errCode;
        this.status = status;
        this.msg = errCode.getMsg();
    }

    @Override
    public byte getFirstByte() {
        return (byte) 0xFF;
    }

    @Override
    public List<byte[]> toByteNoMarkIndex() {
        List<byte[]> listResult = new ArrayList<>();
        // 错误编号
        byte[] e = MysqlUtil.mergeLengthCodedBinary(errCode.getCode());
        listResult.add(e);
        // 服务器状态标志,恒为 '#'
        listResult.add(new byte[]{'#'});
        // 服务器状态
        byte[] e1 = MysqlUtil.mergeLengthCodedBinary(status.getCode());
        listResult.add(e1);
        // 添加服务器消息
        byte[] bytes1 = msg.getBytes(StandardCharsets.UTF_8);
        listResult.add(bytes1);

        return Arrays.asList(MysqlUtil.mergeListBytes(listResult));
    }


    public MysqlErrCodeEnum getErrCode() {
        return errCode;
    }

    public MysqlServerStatusEnum getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}
