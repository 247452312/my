package indi.uhyils.enums;

import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 15时10分
 */
public enum ServiceCode {
    /**
     *
     */
    SUCCESS(200, "操作成功"),
    SUCCESS_REDIS(201, "操作成功,返回值缓存在redis中"),
    REQUEST_PARAM_ERROR(400, "前台传值错误"),
    NONE_AUTH_ERROR(401, "您没有权限"),
    LOGIN_TIME_OUT_ERROR(402, "登录已过期"),
    NO_LOGIN__ERROR(403, "未登录"),
    SPIDER_VERIFICATION(404, "爬虫验证"),
    ASSERT_EXCEPTION(408, "断言异常"),
    SPIDER_VERIFICATION_RUN(407, "您的请求已经被执行,请填写爬虫验证"),
    FROZEN_TEMP(405, "临时冻结"),
    REFUSE_VISIT(406, "您已被拒绝访问"),
    ERROR(500, "服务器内部错误");

    private Integer text;

    private String msg;

    ServiceCode(Integer text, String msg) {
        this.text = text;
        this.msg = msg;
    }

    public static Optional<ServiceCode> getByText(Integer text) {
        for (ServiceCode code : ServiceCode.values()) {
            if (code.getText().equals(text)) {
                return Optional.of(code);
            }
        }
        return Optional.empty();
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
