package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月22日 08时12分
 */
public class AddInterfaceCommand extends AbstractCommand {

    private static final long serialVersionUID = -1L;

    /**
     * 服务提供方id
     */
    private String providerUniqueKey;

    /**
     * 父类id,父类写连表sql,子类写接口
     */
    private Long pid;

    /**
     * 类型 0->数据库 1->mq 2->接口
     */
    private Integer type;

    /**
     * 类型对应的id,例如类型是0数据库,此字段就是数据库表id
     */
    private Long markId;

    /**
     * sql语句,详情见sql规则文档
     */
    private String sql;

    public String getProviderUniqueKey() {
        return providerUniqueKey;
    }

    public void setProviderUniqueKey(String providerUniqueKey) {
        this.providerUniqueKey = providerUniqueKey;
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
}