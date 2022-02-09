package indi.uhyils.pojo.DTO.request;

import java.util.List;

/**
 * 前端传入的数据库信息,除了标记前端不传的{@link DbInformationDTO#bigProjectName}和{@link DbInformationDTO#smallProjectName}之外 其他的都属于必传项
 * 其中类型见{@link indi.uhyils.enum_.DbTypeEnum} 暂时支持mysql oracle 两种
 * 其中 {@link DbInformationDTO#tables} 可以使用_匹配单个字符 或者使用%匹配多个字符
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 07时08分
 */
public class DbInformationDTO {

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
     * 此项目端口
     */
    private Integer port;

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

    /**
     * 作者
     */
    private String author;

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
        this.smallProjectName = projectName.substring(0, 1).toLowerCase() + projectName.substring(1);
        this.bigProjectName = projectName.substring(0, 1).toUpperCase() + projectName.substring(1);
    }

    public String getBigProjectName() {
        return bigProjectName;
    }

    public String getSmallProjectName() {
        return smallProjectName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
