package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 转换节点表表(Node)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public class NodeDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 对应低代码sql
     */
    private String sql;

    /**
     * 下级接口id,多个使用分号隔开
     */
    private String subNode;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("name", getName())
            .append("sql", getSql())
            .append("subNode", getSubNode())
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

}
