package indi.uhyils.enum_;

/**
 * 推送类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月29日 07时11分
 */
public enum PushTypeEnum {
    /**
     * 邮件推送
     */
    EMAIL(1),
    /**
     * 页面推送
     */
    PAGE(2);
    /**
     * 推送编码
     */
    private Integer code;

    PushTypeEnum(Integer code) {
        this.code = code;
    }

    public static PushTypeEnum prase(Integer pushTypeCode) {
        switch (pushTypeCode) {
            case 1:
                return EMAIL;
            case 2:
                return PAGE;
            default:
                return null;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
