package indi.uhyils.enums;

import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时28分
 */
public enum LogDetailTypeEnum {
    /**
     * 详情表,包含了sql,MQ,RPC,task,controller等
     */
    DETAIL('&'),
    /**
     * link表,包含了具体走向
     */
    LINK('^'),
    /**
     * 日志表,包含了所有日志 info以上级别
     */
    LOG('*');

    private Character code;

    LogDetailTypeEnum(Character code) {
        this.code = code;
    }

    public static Optional<LogDetailTypeEnum> parse(char ch) {
        for (LogDetailTypeEnum value : values()) {
            if (value.getCode() == ch) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
        //        throw new RuntimeException("没有发现指定的前缀" + ch);
    }

    public Character getCode() {
        return code;
    }

    public void setCode(Character code) {
        this.code = code;
    }
}
