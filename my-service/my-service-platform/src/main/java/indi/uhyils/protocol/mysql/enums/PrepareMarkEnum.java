package indi.uhyils.protocol.mysql.enums;

import java.util.Objects;

/**
 * 预处理标志位
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月06日 15时04分
 */
public enum PrepareMarkEnum {
    /**
     * 游标
     */
    CURSOR_TYPE_NO_CURSOR((byte) 0x00, "无游标"),
    CURSOR_TYPE_READ_ONLY((byte) 0x01, "只读游标"),
    CURSOR_TYPE_FOR_UPDATE((byte) 0x02, "更新的游标类型"),
    CURSOR_TYPE_SCROLLABLE((byte) 0x04, "游标类型可滚动");

    private final byte code;

    private final String msg;

    PrepareMarkEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static PrepareMarkEnum parse(byte code) {
        for (PrepareMarkEnum value : values()) {
            if (Objects.equals(code, value.getCode())) {
                return value;
            }
        }
        return null;
    }

    public byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
