package indi.uhyils.enums;


import indi.uhyils.MysqlUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 14时32分
 */
public enum FieldMarkEnum {
    /**
     * 不为空
     */
    NOT_NULL_FLAG((short) 0x0001),
    /**
     * 主键
     */
    PRI_KEY_FLAG((short) 0x0002),
    /**
     * 唯一索引
     */
    UNIQUE_KEY_FLAG((short) 0x0004),
    /**
     * 多键
     */
    MULTIPLE_KEY_FLAG((short) 0x0008),
    /**
     * blob类型
     */
    BLOB_FLAG((short) 0x0010),
    /**
     * 未签名
     */
    UNSIGNED_FLAG((short) 0x0020),
    /**
     * 零填充
     */
    ZEROFILL_FLAG((short) 0x0040),
    /**
     * 二进制数据
     */
    BINARY_FLAG((short) 0x0080),
    /**
     * 枚举类型
     */
    ENUM_FLAG((short) 0x0100),
    /**
     * 自增标志
     */
    AUTO_INCREMENT_FLAG((short) 0x0200),
    /**
     * 时间标志
     */
    TIMESTAMP_FLAG((short) 0x0400),
    /**
     * 不造啥标志
     */
    SET_FLAG((short) 0x0800);

    private final short code;

    FieldMarkEnum(short code) {
        this.code = code;
    }

    public short getCode() {
        return code;
    }

    public byte[] getByteCode() {
        return MysqlUtil.toBytes(code);
    }
}
