package indi.uhyils.pojo.model;

import indi.uhyils.enum_.OrderNodeRunTypeEnum;
import indi.uhyils.enum_.OrderNodeTypeEnum;
import indi.uhyils.pojo.model.base.BaseDoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderBaseNodeEntity extends BaseDoEntity {

    /**
     * 基础表id
     */
    private Long baseInfoId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点描述
     */
    private String desc;

    /**
     * 处理人id
     */
    private Long runDealUserId;

    /**
     * 抄送人id
     */
    private Long noticeUserId;

    /**
     * 处理类型 {@link OrderNodeRunTypeEnum}
     */
    private Integer runType;

    /**
     * 节点类型 {@link OrderNodeTypeEnum}
     */
    private Integer type;

    /**
     * 限时(分钟)
     */
    private Integer limitTime;

    /**
     * 节点初始化事件id
     */
    private Long initApiId;

    /**
     * 节点执行事件id
     */
    private Long runApiId;

    /**
     * 保存执行事件id
     */
    private Long saveApiId;

    /**
     * 流转执行事件id
     */
    private Long transApiId;

    /**
     * 是否同步
     */
    private Integer sync;

    /**
     * 是否隐藏,如果隐藏,则在创建项目时不自动添加此节点
     */
    private Boolean hidden;


    public Long getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(Long baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getRunDealUserId() {
        return runDealUserId;
    }

    public void setRunDealUserId(Long runDealUserId) {
        this.runDealUserId = runDealUserId;
    }

    public Long getNoticeUserId() {
        return noticeUserId;
    }

    public void setNoticeUserId(Long noticeUserId) {
        this.noticeUserId = noticeUserId;
    }

    public Integer getRunType() {
        return runType;
    }

    public void setRunType(Integer runType) {
        this.runType = runType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public Long getInitApiId() {
        return initApiId;
    }

    public void setInitApiId(Long initApiId) {
        this.initApiId = initApiId;
    }

    public Long getRunApiId() {
        return runApiId;
    }

    public void setRunApiId(Long runApiId) {
        this.runApiId = runApiId;
    }

    public Long getSaveApiId() {
        return saveApiId;
    }

    public void setSaveApiId(Long saveApiId) {
        this.saveApiId = saveApiId;
    }

    public Long getTransApiId() {
        return transApiId;
    }

    public void setTransApiId(Long transApiId) {
        this.transApiId = transApiId;
    }

    public Integer getSync() {
        return sync;
    }

    public void setSync(Integer sync) {
        this.sync = sync;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
