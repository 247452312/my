package indi.uhyils.mysql.pojo.DTO;

import java.io.Serializable;

/**
 * 全局系统参数
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月22日 09时58分
 */
public class GlobalVariablesInfo implements Serializable {

    /**
     * 参数名称
     */
    private String variableName;

    /**
     * 参数值
     */
    private Object variableValue;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Object getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(Object variableValue) {
        this.variableValue = variableValue;
    }
}
