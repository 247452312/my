package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月11日 15时19分
 */
public class PublishDbCommand extends AbstractCommand {

    /**
     * 密码
     */
    private String password;

    /**
     * 资源主表id
     */
    private Long sourceId;

    /**
     * 数据库类型, 0 mysql 1 oracle
     */
    private Integer type;

    /**
     * 数据库url
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 名称
     */
    private String name;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
