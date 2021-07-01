package indi.uhyils.enum_;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月25日 07时21分
 */
public enum ApiCodeEnum {

    /**
     * 同name
     */
    /**
     * 操作成功
     */
    SUCCESS("操作成功", 200),

    /**
     * 上一节点传值错误
     */
    LAST_CODE_PARAM_ERROR("上一节点传值错误", 400),

    /**
     * 服务器内部错误
     */
    ERROR("服务器内部错误", 500);


    private String name;
    private Integer code;

    ApiCodeEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
