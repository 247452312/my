package indi.uhyils.pojo.DTO;


/**
 * 推送日志表(PushMsg)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分06秒
 */
public class PushMsgDTO extends IdDTO {

    private static final long serialVersionUID = -19553570459311920L;


    private String content;

    private Boolean success;

    private Long target;

    private String title;

    private Integer type;


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


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
