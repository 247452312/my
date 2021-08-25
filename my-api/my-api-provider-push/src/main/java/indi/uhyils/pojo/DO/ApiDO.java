package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;

/**
 * 外界api调用信息类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月27日 17时13分
 */
public class ApiDO extends BaseDoDO {


    /**
     * api所在组(群)
     */
    private Long apiGroupId;

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
     * 结果编码
     */
    private String resultCode;

    public Long getApiGroupId() {
        return apiGroupId;
    }

    public void setApiGroupId(Long apiGroupId) {
        this.apiGroupId = apiGroupId;
    }

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

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
