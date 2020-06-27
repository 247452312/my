package indi.uhyils.enum_;

/**
 * 请求类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月08日 14时10分
 */
public enum RequestTypeEnum {
    /**
     * 页面请求
     */
    HTML_REQUEST(1),
    /**
     * 其他请求
     */
    OTHER_REQUEST(0);

    private Integer type;

    RequestTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
