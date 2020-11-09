package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

import java.util.*;

/**
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderApiEntity extends BaseVoEntity {

	/**
    * bean名称
    */
 	private String beanName;

	/**
    * 方法名称
    */
 	private String methodName;


	public String getBeanName(){
		return beanName;
	}

	public String getMethodName(){
		return methodName;
	}


	public void setBeanName(String beanName){
		this.beanName = beanName;
	}

	public void setMethodName(String methodName){
		this.methodName = methodName;
	}


}
