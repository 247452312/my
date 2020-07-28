package indi.uhyils.pojo.request;

import java.util.List;

/**
 * 数据库信息(项目生成用)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 07时08分
 */
public class DbInformation {
    /**
     * 数据库连接串
     */
    private String url;

    /**
     * 数据库名称
     */
    private String dbName;
    /**
     * 项目包名称
     */
    private String projectName;

    /**
     * 前台不传 项目名首字母大写
     */
    private String bigProjectName;
    /**
     * 前台不传 项目名首字母小写
     */
    private String smallProjectName;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    /**
     * 这次要导出的表
     */
    private List<String> tables;

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        this.bigProjectName = projectName.substring(0, 1).toUpperCase() + projectName.substring(1);
        this.smallProjectName = projectName.substring(0, 1).toLowerCase() + projectName.substring(1);
    }

    public String getBigProjectName() {
        return bigProjectName;
    }

    public String getSmallProjectName() {
        return smallProjectName;
    }
}
