package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.enum_.InterfaceTypeEnum;

/**
 * 数据库连接表(DbInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分07秒
 */
@TableName(value = "sys_db_info")
public class DbInfoDO extends SourceInfoDO {

    private static final long serialVersionUID = 326224445123463902L;

    /**
     * 名称
     */
    @TableField
    private String name;

    /**
     * 数据库url
     */
    @TableField
    private String url;

    /**
     * 数据库类型 0->mysql 1->oracle
     */
    @TableField
    private Integer type;

    /**
     * 用户名
     */
    @TableField
    private String username;

    /**
     * 密码
     */
    @TableField
    private String password;

    /**
     * sql语句
     */
    @TableField
    private String sqlStr;


    /**
     * 所属生产者
     */
    @TableField
    private Long providerId;


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

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    @Override
    public InterfaceTypeEnum getSourceType() {
        return InterfaceTypeEnum.DB;
    }
}
