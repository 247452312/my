package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * 外界api调用信息类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月27日 17时13分
 */
public class ApiEntity extends BaseVoEntity {

    /**
     * 此api的名称
     */
    private String name;

    /**
     * api所在组(群)
     */
    private String apiGroup;
    /**
     * 调用顺位
     */
    private Integer apiOrder;

    /**
     * 此api在调用顺位中是否需要重复
     */
    private Boolean needRepeat;

    /**
     * 请求网址
     */
    private String url;

    /**
     * 请求类型
     */
    private String type;

    /**
     * 请求头
     */
    private String head;

    /**
     * 请求限制频率
     */
    private Integer frequency;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 结果格式
     */
    private String resultFormat;


    public Integer getApiOrder() {
        return apiOrder;
    }

    public void setApiOrder(Integer apiOrder) {
        this.apiOrder = apiOrder;
    }

    public Boolean getNeedRepeat() {
        return needRepeat;
    }

    public void setNeedRepeat(Boolean needRepeat) {
        this.needRepeat = needRepeat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResultFormat() {
        return resultFormat;
    }

    public void setResultFormat(String resultFormat) {
        this.resultFormat = resultFormat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiGroup() {
        return apiGroup;
    }

    public void setApiGroup(String apiGroup) {
        this.apiGroup = apiGroup;
    }
}
