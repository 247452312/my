package indi.uhyils.protocol.mysql.handler;

import java.io.Serializable;

/**
 * mysql此次请求的信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 19时28分
 */
public class MysqlThisRequestInfo implements Serializable {

    /**
     * 此次请求的mysql原始代码
     */
    private final byte[] mysqlBytes;

    public MysqlThisRequestInfo(byte[] mysqlBytes) {
        this.mysqlBytes = mysqlBytes;
    }

    public byte[] getMysqlBytes() {
        return mysqlBytes;
    }
}
