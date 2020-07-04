package indi.uhyils.util.mail;


import java.util.List;

/**
 * 发送邮件时，接收参数的类
 *
 * @author uhyils@qq.com
 */
public class EmailVo {

    /**
     * 收件人，支持多个收件人，用逗号分隔
     */
    private List<String> tos;

    private String subject;

    private String content;


    public List<String> getTos() {
        return tos;
    }

    public void setTos(List<String> tos) {
        this.tos = tos;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

