package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 数据库资源表表(PlatformSourceDb)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public class PlatformSourceDbDTO extends IdDTO {

    private static final long serialVersionUID = -1L;
    
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
    
    public void setPassword(String password) {
            this.password = password;
        }

    public String getPassword() {
            return password;
        }
        
    public void setSourceId(Long sourceId) {
            this.sourceId = sourceId;
        }

    public Long getSourceId() {
            return sourceId;
        }
        
    public void setType(Integer type) {
            this.type = type;
        }

    public Integer getType() {
            return type;
        }
        
    public void setUrl(String url) {
            this.url = url;
        }

    public String getUrl() {
            return url;
        }
        
    public void setUsername(String username) {
            this.username = username;
        }

    public String getUsername() {
            return username;
        }
        

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("password", getPassword())
                .append("id", getId())
                .append("sourceId", getSourceId())
                .append("type", getType())
                .append("url", getUrl())
                .append("username", getUsername())
                .toString();
    }

}
