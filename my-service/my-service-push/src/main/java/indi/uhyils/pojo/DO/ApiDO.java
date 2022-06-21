package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * api表(Api)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时16分22秒
 */
@TableName(value = "sys_api")
public class ApiDO extends BaseDO {

    private static final long serialVersionUID = -68428008576716777L;


    @TableField
    private Integer apiOrder;

    @TableField
    private Integer frequency;

    @TableField
    private String head;

    @TableField
    private Boolean needRepeat;

    @TableField
    private String param;

    @TableField
    private String type;

    @TableField
    private String url;

    @TableField
    private String resultCode;

    @TableField
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
