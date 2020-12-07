package indi.uhyils.pojo.model;

import indi.uhyils.enum_.OrderNodeResultTypeEnum;
import indi.uhyils.enum_.OrderNodeRunTypeEnum;
import indi.uhyils.enum_.OrderNodeStatusEnum;
import indi.uhyils.enum_.OrderNodeTypeEnum;
import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderNodeEntity extends BaseVoEntity {

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
     * 节点状态 {@link OrderNodeStatusEnum}
     */
    private Integer status;

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
     * 流转事件的id
     */
    private Long transApiId;

    /**
     * 处理结果类型 {@link OrderNodeResultTypeEnum}
     */
    private Integer resultType;

    /**
     * 处理结果id
     */
    private Long resultId;


    /**
     * 是否同步
     */
    private Integer sync;


    /**
     * 处理人建议
     */
    private String suggest;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Integer getSync() {
        return sync;
    }

    public void setSync(Integer sync) {
        this.sync = sync;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
