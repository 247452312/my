package indi.uhyils.pojo.DTO;


/**
 * 推送日志信息表(PushPageMsg)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分11秒
 */
public class PushPageMsgDTO extends IdDTO {

    private static final long serialVersionUID = 470386862399248256L;


    private String content;

    private String title;

    private Long userId;

    private Boolean view;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

}
