package indi.uhyils.enum_;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 15时10分
 */
public enum ServiceCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 前台传值错误
     */
    REQUEST_PARAM_ERROR(400, "前台传值错误"),

    /**
     * 没有权限错误
     */
    NONE_AUTH_ERROR(401, "您没有权限"),

    /**
     * 登录已过期
     */
    LOGIN_TIME_OUT_ERROR(402, "登录已过期"),

    /**
     * 未登录
     */
    NO_LOGIN__ERROR(403, "未登录"),

    /**
     * 爬虫验证
     */
    SPIDER_VERIFICATION(404, "爬虫验证"),
    /**
     * 业务已经执行完毕的爬虫验证
     */
    SPIDER_VERIFICATION_RUN(407, "您的请求已经被执行,请填写爬虫验证"),
    /**
     * 临时冻结
     */
    FROZEN_TEMP(405, "临时冻结"),

    /**
     * 拒绝此ip访问
     */
    REFUSE_VISIT(406, "您已被拒绝访问"),

    /**
     * 服务器内部错误
     */
    ERROR(500, "服务器内部错误");

    private Integer text;
    private String msg;

    ServiceCode(Integer text, String msg) {
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
