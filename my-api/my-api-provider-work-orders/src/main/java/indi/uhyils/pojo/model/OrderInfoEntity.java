package indi.uhyils.pojo.model;
import indi.uhyils.pojo.model.base.BaseVoEntity;
import java.util.*;

/**
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderInfoEntity extends BaseVoEntity {

	/**
    * 优先级 0->普通 1->加急
    */
 	private Integer priority;

	/**
    * 可查询人id
    */
 	private String queryUserIds;

	/**
    * 运行时限(分钟)
    */
 	private Integer limitTime;

	/**
    * 是否是子流程 0->不是 1->是
    */
 	private Integer son;

	/**
    * 监管人id
    */
 	private String monitorUserId;

	/**
    * 工单名称
    */
 	private String name;

	/**
    * 工单描述
    */
 	private String desc;

	/**
    * 运行状态
0->停用
1->启用
2->撤回中
3->已撤回
4->停用中
5->回退中
    */
 	private Integer status;


	public Integer getPriority(){
		return priority;
	}

	public String getQueryUserIds(){
		return queryUserIds;
	}

	public Integer getLimitTime(){
		return limitTime;
	}

	public Integer getSon(){
		return son;
	}

	public String getMonitorUserId(){
		return monitorUserId;
	}

	public String getName(){
		return name;
	}

	public String getDesc(){
		return desc;
	}

	public Integer getStatus(){
		return status;
	}


	public void setPriority(Integer priority){
		this.priority = priority;
	}

	public void setQueryUserIds(String queryUserIds){
		this.queryUserIds = queryUserIds;
	}

	public void setLimitTime(Integer limitTime){
		this.limitTime = limitTime;
	}

	public void setSon(Integer son){
		this.son = son;
	}

	public void setMonitorUserId(String monitorUserId){
		this.monitorUserId = monitorUserId;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public void setStatus(Integer status){
		this.status = status;
	}


}
