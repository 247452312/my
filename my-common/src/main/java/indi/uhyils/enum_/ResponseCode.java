package indi.uhyils.enum_;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 15时10分
 */
public enum ResponseCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 操作失败
     */
    FAILED(400, "操作失败"),

    /**
     * 业务异常，比如需要删除一条数据数据库没有
     */
    BUSI_EXCEPTION(404, "业务异常"),

    /**
     * 全局异常，比如找不到资源，没有权限
     */
    OVERALL_EXCEPTION(405, "全局异常"),

    /**
     * 服务器内部错误
     */
    ERROR(500, "服务器内部错误");

    private Integer text;
    private String msg;

    ResponseCode(Integer text, String msg) {
        this.text = text;
        this.msg = msg;
    }

    public Integer getText() {
        return text;
    }

    public void setText(Integer text) {
        this.text = text;
    }
}
