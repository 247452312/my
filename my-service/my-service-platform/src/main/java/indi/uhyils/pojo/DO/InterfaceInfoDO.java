package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 接口信息表(InterfaceInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@TableName(value = "sys_interface_info")
public class InterfaceInfoDO extends BaseDO {

    private static final long serialVersionUID = -68644526253503452L;

    /**
     * 接口名称,在mysql中为表名
     */
    @TableField
    private String name;

    /**
     * 服务提供方id
     */
    @TableField
    private Long providerId;

    /**
     * 父类id,父类写连表sql,子类写接口
     */
    @TableField
    private Long pid;

    /**
     * 类型 0->数据库 1->mq 2->接口
     */
    @TableField
    private Integer type;

    /**
     * 类型对应的id,例如类型是0数据库,此字段就是数据库表id
     */
    @TableField
    private Long markId;

    /**
     * sql语句,详情见sql规则文档
     */
    @TableField("`sql`")
    private String sql;


    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
