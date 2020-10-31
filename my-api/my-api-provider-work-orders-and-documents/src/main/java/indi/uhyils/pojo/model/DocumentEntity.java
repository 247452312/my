package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * 文档
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月27日 01时06分
 */
public class DocumentEntity extends BaseVoEntity {

    /**
     * 文档附件id
     */
    private String enclosureId;

    /**
     * 上级目录
     */
    private String parentId;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档内容
     */
    private String content;

    /**
     * 使用场景代号
     */
    private Integer iframe;


    public String getEnclosureId() {
        return enclosureId;
    }

    public String getParentId() {
        return parentId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


    public void setEnclosureId(String enclosureId) {
        this.enclosureId = enclosureId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIframe() {
        return iframe;
    }

    public void setIframe(Integer iframe) {
        this.iframe = iframe;
    }
}
