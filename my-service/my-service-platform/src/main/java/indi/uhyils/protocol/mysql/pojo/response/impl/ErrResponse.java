package indi.uhyils.protocol.mysql.pojo.response.impl;

import indi.uhyils.protocol.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.pojo.response.AbstractMysqlResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    public ErrResponse(MysqlErrCodeEnum errCode, MysqlServerStatusEnum status, String msg) {
        this.errCode = errCode;
        this.status = status;
        this.msg = msg;
    }

    public ErrResponse(MysqlErrCodeEnum errCode, MysqlServerStatusEnum status) {
        this.errCode = errCode;
        this.status = status;
        this.msg = errCode.getMsg();
    }

    @Override
    public byte getFirstByte() {
        return (byte) 0xFF;
    }

    @Override
    public byte[] toByteNoMarkIndex() {
        List<byte[]> listResult = new ArrayList<>();
        int count = 0;
        // 错误编号
        byte[] e = MysqlUtil.mergeLengthCodedBinary(errCode.getCode());
        listResult.add(e);
        count += e.length;
        // 服务器状态标志,恒为 '#'
        listResult.add(new byte[]{'#'});
        // 服务器状态
        byte[] e1 = MysqlUtil.mergeLengthCodedBinary(status.getCode());
        listResult.add(e1);
        count += e1.length;
        // 添加服务器消息
        byte[] bytes1 = msg.getBytes(StandardCharsets.UTF_8);
        listResult.add(bytes1);
        count += bytes1.length;

        return MysqlUtil.mergeListBytes(listResult, count);
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
