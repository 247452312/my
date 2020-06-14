package indi.uhyils.enum_;

/**
 * 软件状态
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 21时14分
 */
public enum SoftwareStatusEnum {
    /**
     * 运行中
     */
    RUNNING(1),
    /**
     * 停止
     */
    STOP(2),
    /**
     * 出错
     */
    ERROR(3);

    private Integer status;

    SoftwareStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
