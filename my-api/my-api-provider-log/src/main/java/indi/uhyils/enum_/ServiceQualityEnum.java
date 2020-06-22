package indi.uhyils.enum_;

/**
 * 服务运行质量 其中 code是递增的 越高代表越严重
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 08时19分
 */
public enum ServiceQualityEnum {
    /**
     * 没有问题
     */
    GOOD(0),
    /**
     * 内存泄漏
     */
    OUT_OF_MEMORY(1),
    /**
     * 超出总内存最大值的80%
     */
    OUT_OF_JVM_MAX_MEMORY(1),
    /**
     * 超出堆内存最大值的80%
     */
    OUT_OF_HEAP_MAX_MEMORY(1),
    /**
     * 超出非堆内存最大值的80%
     */
    OUT_OF_NO_HEAP_MAX_MEMORY(1),
    /**
     * 持续不足初始内存的50%
     */
    NOT_ENOUGH_JVM_MAX_MEMORY(1),
    /**
     * 持续不足堆始内存的50%
     */
    NOT_ENOUGH_HEAP_MAX_MEMORY(1),
    /**
     * 持续不足非堆始内存的50%
     */
    NOT_ENOUGH_NO_HEAP_MAX_MEMORY(1),
    /**
     * 错误 服务意外停止了
     */
    ERROR(2);

    /**
     * code
     */
    private Integer code;

    ServiceQualityEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
