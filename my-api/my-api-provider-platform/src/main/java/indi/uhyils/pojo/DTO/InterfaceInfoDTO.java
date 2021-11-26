package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 接口信息表(InterfaceInfo)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分40秒
 */
public class InterfaceInfoDTO extends IdDTO {

    private static final long serialVersionUID = 439985319346813742L;


    /**
     * 服务提供方id
     */
    private Long providerId;

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
    private String sqlStr;

    /**
     * 接口名称,在mysql中为表名
     */
    private String name;


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

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
