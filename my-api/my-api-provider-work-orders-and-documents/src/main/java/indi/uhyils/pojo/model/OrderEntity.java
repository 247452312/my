package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

import java.util.*;

/**
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月27日 01时06分
 */
public class OrderEntity extends BaseVoEntity {

	/**
    * 附件id
    */
 	private String enclosureId;

	/**
    * 工单标题
    */
 	private String title;

	/**
    * 优先级 1->紧急 0->一般
    */
 	private Integer priority;

	/**
    * 工单内容
    */
 	private String content;

	/**
    * 工单状态 0->未完成 1->已完成
    */
 	private Integer status;


	public String getEnclosureId(){
		return enclosureId;
	}

	public String getTitle(){
		return title;
	}

	public Integer getPriority(){
		return priority;
	}

	public String getContent(){
		return content;
	}

	public Integer getStatus(){
		return status;
	}


	public void setEnclosureId(String enclosureId){
		this.enclosureId = enclosureId;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public void setPriority(Integer priority){
		this.priority = priority;
	}

	public void setContent(String content){
		this.content = content;
	}

	public void setStatus(Integer status){
		this.status = status;
	}


}
