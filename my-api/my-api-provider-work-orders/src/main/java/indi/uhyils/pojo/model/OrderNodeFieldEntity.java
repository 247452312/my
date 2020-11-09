package indi.uhyils.pojo.model;
import indi.uhyils.pojo.model.base.BaseVoEntity;
import java.util.*;

/**
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderNodeFieldEntity extends BaseVoEntity {

	/**
    * 数值类型 1->字符串 2->数值 3->只允许英文 4->email 5->日期)
    */
 	private Integer valueType;

	/**
    * 是否可编辑
    */
 	private Integer edit;

	/**
    * 默认值
    */
 	private String devaultValue;

	/**
    * 字段类型(1->编辑框 2->单选框 3->多选框 4->下拉框 5->文本框)
    */
 	private Integer type;

	/**
    * 是否可以为空
    */
 	private Integer empty;

	/**
    * 属性名称
    */
 	private String name;

	/**
    * 节点id
    */
 	private String baseOrderId;

	/**
    * 属性备注
    */
 	private String desc;


	public Integer getValueType(){
		return valueType;
	}

	public Integer getEdit(){
		return edit;
	}

	public String getDevaultValue(){
		return devaultValue;
	}

	public Integer getType(){
		return type;
	}

	public Integer getEmpty(){
		return empty;
	}

	public String getName(){
		return name;
	}

	public String getBaseOrderId(){
		return baseOrderId;
	}

	public String getDesc(){
		return desc;
	}


	public void setValueType(Integer valueType){
		this.valueType = valueType;
	}

	public void setEdit(Integer edit){
		this.edit = edit;
	}

	public void setDevaultValue(String devaultValue){
		this.devaultValue = devaultValue;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public void setEmpty(Integer empty){
		this.empty = empty;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setBaseOrderId(String baseOrderId){
		this.baseOrderId = baseOrderId;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}


}
