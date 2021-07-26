package indi.uhyils.log;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月23日 09时11分
 */
public enum LogTypeEnum {
    /**
     * 意思... 看名称就懂了吧
     */
    RPC(1),
    MQ(2),
    DB(3),
    TASK(4);

    private Integer code;


    LogTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
