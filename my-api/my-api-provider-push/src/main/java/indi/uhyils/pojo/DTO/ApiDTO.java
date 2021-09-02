package indi.uhyils.pojo.DTO;


/**
 * api表(Api)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分48秒
 */
public class ApiDTO extends IdDTO {

    private static final long serialVersionUID = -22058505117108319L;


    private Integer apiOrder;

    private Integer frequency;

    private String head;

    private Boolean needRepeat;

    private String param;

    private String type;

    private String url;

    private String resultCode;

    private Long apiGroupId;


    public Integer getApiOrder() {
        return apiOrder;
    }

    public void setApiOrder(Integer apiOrder) {
        this.apiOrder = apiOrder;
    }


    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }


    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }


    public Boolean getNeedRepeat() {
        return needRepeat;
    }

    public void setNeedRepeat(Boolean needRepeat) {
        this.needRepeat = needRepeat;
    }


    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }


    public Long getApiGroupId() {
        return apiGroupId;
    }

    public void setApiGroupId(Long apiGroupId) {
        this.apiGroupId = apiGroupId;
    }

}
