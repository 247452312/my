package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderBaseNodeEntity extends BaseVoEntity {

    /**
     * 基础表id
     */
    private String baseInfoId;

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
    private String runDealUserId;

    /**
     * 抄送人id
     */
    private String noticeUserId;

    /**
     * 处理类型 {@link indi.uhyils.enum_.NodeRunTypeEnum}
     */
    private Integer runType;

    /**
     * 节点类型 {@link indi.uhyils.enum_.NodeTypeEnum}
     */
    private Integer type;

    /**
     * 限时(分钟)
     */
    private Integer limitTime;

    /**
     * 节点初始化事件id
     */
    private String initApiId;

    /**
     * 节点执行事件id
     */
    private String runApiId;

    /**
     * 保存执行事件id
     */
    private String saveApiId;

    /**
     * 流转执行事件id
     */
    private String transApiId;

    /**
     * 是否同步
     */
    private Integer sync;


    public String getSaveApiId() {
        return saveApiId;
    }

    public void setSaveApiId(String saveApiId) {
        this.saveApiId = saveApiId;
    }

    public String getInitApiId() {
        return initApiId;
    }

    public void setInitApiId(String initApiId) {
        this.initApiId = initApiId;
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

    public Integer getSync() {
        return sync;
    }

    public void setSync(Integer sync) {
        this.sync = sync;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }


    public String getNoticeUserId() {
        return noticeUserId;
    }

    public void setNoticeUserId(String noticeUserId) {
        this.noticeUserId = noticeUserId;
    }

    public String getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    public String getRunDealUserId() {
        return runDealUserId;
    }

    public void setRunDealUserId(String runDealUserId) {
        this.runDealUserId = runDealUserId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRunApiId() {
        return runApiId;
    }

    public void setRunApiId(String runApiId) {
        this.runApiId = runApiId;
    }

    public String getTransApiId() {
        return transApiId;
    }

    public void setTransApiId(String transApiId) {
        this.transApiId = transApiId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
