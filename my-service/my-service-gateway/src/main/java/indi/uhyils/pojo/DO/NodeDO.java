package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 转换节点表(Node)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月16日 10时28分
 */
@TableName(value = "sys_node")
public class NodeDO extends BaseDO {

    private static final long serialVersionUID = -1L;

    /**
     * 节点名称
     */
    @TableField
    private String name;

    /**
     * 对应低代码sql
     */
    @TableField
    private String sql;

    /**
     * 下级接口id,多个使用分号隔开
     */
    @TableField
    private String subNode;

    /**
     * 对应唯一标识(url形式),如果是mysql调用,则使用全称拼写,例如库名/表名
     */
    @TableField
    private String url;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("name", getName())
            .append("sql", getSql())
            .append("subNode", getSubNode())
            .append("url", getUrl())
            .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSubNode() {
        return subNode;
    }

    public void setSubNode(String subNode) {
        this.subNode = subNode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
