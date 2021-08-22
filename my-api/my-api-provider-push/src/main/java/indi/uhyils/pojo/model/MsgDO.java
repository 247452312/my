package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;

/**
 * 消息entity
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月29日 07时53分
 */
public class MsgDO extends BaseDoDO {

    /**
     * 发送类型
     */
    private Integer type;

    /**
     * 目标userId
     */
    private Long target;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否成功
     */
    private Boolean success;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
