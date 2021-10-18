package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 数据库连接表(DbInfo)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分40秒
 */
public class DbInfoDTO extends IdDTO {

    private static final long serialVersionUID = -51855984020278252L;


    /**
     * 名称
     */
    private String name;

    /**
     * 数据库url
     */
    private String url;

    /**
     * 数据库类型 0->mysql 1->oracle
     */
    private Integer type;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
