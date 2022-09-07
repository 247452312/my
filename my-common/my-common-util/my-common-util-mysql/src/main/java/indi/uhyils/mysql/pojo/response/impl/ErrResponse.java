package indi.uhyils.mysql.pojo.response.impl;

import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.pojo.response.AbstractMysqlResponse;
import indi.uhyils.mysql.util.MysqlUtil;
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

    public ErrResponse(MysqlErrCodeEnum errCode, MysqlServerStatusEnum status, String msg) {
        super();
        this.errCode = errCode;
        this.status = status;
        this.msg = msg;
    }

    public ErrResponse(MysqlErrCodeEnum errCode, MysqlServerStatusEnum status) {
        super();
        this.errCode = errCode;
        this.status = status;
        this.msg = errCode.getMsg();
    }

    public static ErrResponse build(MysqlErrCodeEnum errCode, MysqlServerStatusEnum status, String msg) {
        return new ErrResponse(errCode, status, msg);
    }

    @Override
    public byte getFirstByte() {
        return (byte) 0xFF;
    }

    @Override
    public List<byte[]> toByteNoMarkIndex() {
        List<byte[]> listResult = new ArrayList<>();
        // header 恒为 FF
        listResult.add(new byte[]{(byte) 0xff});
        // 错误编号
        byte[] e = errCode.getByteCode();
        listResult.add(e);
        // 服务器状态标志,恒为 '#'
        listResult.add(new byte[]{'#'});
        // 服务器状态
        byte[] e1 = "HY000".getBytes(StandardCharsets.UTF_8);;
        listResult.add(e1);
        // 添加服务器消息 EOF类型的字符串
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
