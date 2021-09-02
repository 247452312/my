package indi.uhyils.pojo.DTO;


/**
 * api组表(ApiGroup)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分52秒
 */
public class ApiGroupDTO extends IdDTO {

    private static final long serialVersionUID = -50041789210797914L;


    private String name;

    private String resultFormat;

    private Boolean subscribe;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getResultFormat() {
        return resultFormat;
    }

    public void setResultFormat(String resultFormat) {
        this.resultFormat = resultFormat;
    }


    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

}
