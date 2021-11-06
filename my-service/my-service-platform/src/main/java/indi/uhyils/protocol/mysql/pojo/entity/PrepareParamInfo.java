package indi.uhyils.protocol.mysql.pojo.entity;

import indi.uhyils.protocol.mysql.enums.FieldTypeEnum;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月06日 16时33分
 */
public class PrepareParamInfo {

    /**
     * 预处理参数类型
     */
    private final FieldTypeEnum parse;

    /**
     * 预处理参数
     */
    private final Object paramValue;

    public PrepareParamInfo(FieldTypeEnum parse, Object paramValue) {
        this.parse = parse;
        this.paramValue = paramValue;
    }

    public FieldTypeEnum getParse() {
        return parse;
    }

    public Object getParamValue() {
        return paramValue;
    }
}
