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
     * 前台传值错误
     */
    REQUEST_PARM_ERROR(400, "前台传值错误"),

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
