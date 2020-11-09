package indi.uhyils.pojo.model;
import indi.uhyils.pojo.model.base.BaseVoEntity;
import java.util.*;

/**
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderNodeEntity extends BaseVoEntity {

	/**
    * 保存执行事件id
    */
 	private String saveApiId;

	/**
    * 处理结果类型 0->处理成功 1->处理失败 2->转交别人处理
    */
 	private Integer resultType;

	/**
    * 节点初始化事件id
    */
 	private String initApiId;

	/**
    * 是否发送邮件
    */
 	private Integer sendEmail;

	/**
    * 处理类型 0->自动处理 1->人工处理
    */
 	private Integer runType;

	/**
    * 节点类型 0->开始节点 1->中间节点 2->结束节点
    */
 	private Integer type;

	/**
    * 是否同步
    */
 	private Integer sync;

	/**
    * 限时(分钟)
    */
 	private Integer limitTime;

	/**
    * 处理结果
    */
 	private String result;

	/**
    * 抄送人id
    */
 	private String noticeUserId;

	/**
    * 基础表id
    */
 	private String baseInfoId;

	/**
    * 处理人id
    */
 	private String runDealUserId;

	/**
    * 节点名称
    */
 	private String name;

	/**
    * 节点执行事件id
    */
 	private String runApiId;

	/**
    * 是否发送短信
    */
 	private Integer sendSms;

	/**
    * 节点描述
    */
 	private String desc;


	public String getSaveApiId(){
		return saveApiId;
	}

	public Integer getResultType(){
		return resultType;
	}

	public String getInitApiId(){
		return initApiId;
	}

	public Integer getSendEmail(){
		return sendEmail;
	}

	public Integer getRunType(){
		return runType;
	}

	public Integer getType(){
		return type;
	}

	public Integer getSync(){
		return sync;
	}

	public Integer getLimitTime(){
		return limitTime;
	}

	public String getResult(){
		return result;
	}

	public String getNoticeUserId(){
		return noticeUserId;
	}

	public String getBaseInfoId(){
		return baseInfoId;
	}

	public String getRunDealUserId(){
		return runDealUserId;
	}

	public String getName(){
		return name;
	}

	public String getRunApiId(){
		return runApiId;
	}

	public Integer getSendSms(){
		return sendSms;
	}

	public String getDesc(){
		return desc;
	}


	public void setSaveApiId(String saveApiId){
		this.saveApiId = saveApiId;
	}

	public void setResultType(Integer resultType){
		this.resultType = resultType;
	}

	public void setInitApiId(String initApiId){
		this.initApiId = initApiId;
	}

	public void setSendEmail(Integer sendEmail){
		this.sendEmail = sendEmail;
	}

	public void setRunType(Integer runType){
		this.runType = runType;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public void setSync(Integer sync){
		this.sync = sync;
	}

	public void setLimitTime(Integer limitTime){
		this.limitTime = limitTime;
	}

	public void setResult(String result){
		this.result = result;
	}

	public void setNoticeUserId(String noticeUserId){
		this.noticeUserId = noticeUserId;
	}

	public void setBaseInfoId(String baseInfoId){
		this.baseInfoId = baseInfoId;
	}

	public void setRunDealUserId(String runDealUserId){
		this.runDealUserId = runDealUserId;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setRunApiId(String runApiId){
		this.runApiId = runApiId;
	}

	public void setSendSms(Integer sendSms){
		this.sendSms = sendSms;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}


}
