package indi.uhyils.protocol.mysql.pojo.response.impl;

import indi.uhyils.protocol.mysql.pojo.response.AbstractMysqlResponse;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 20时39分
 */
public class ResultSetResponse extends AbstractMysqlResponse {


    @Override
    public byte getFirstByte() {
        return (byte) 0xFF;
    }

    @Override
    public byte[] toByteNoMarkIndex() {
        return new byte[0];
    }
}
