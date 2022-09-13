package indi.uhyils.mysql.pojo.DTO;

import java.io.Serializable;

/**
 * 视图信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月13日 08时34分
 */
public class ParameterInfo implements Serializable {

    /**
     * 始终为def
     */
    private String specificCatalog;

    /**
     * 数据库的名称
     */
    private String specificSchema;

    /**
     * 包含参数的例程的名称
     */
    private String specificName;

    /**
     * 参数的位置，对于存储过程来说，其值为1 2 3 4，对于还是来说，因为该表还包括返回值，所以返回值的位置为0，并且PARAMETER_NAME and PARAMETER_MODE列的值为NULL
     */
    private Long ordinalPosition;

    /**
     * 参数的模式，可以是IN, OUT,或 INOUT，对于函数的返回值，值为NULL
     */
    private String parameterMode;

    /**
     * 参数的名字，对于函数的返回值，其值为NULL
     */
    private String parameterName;

    /**
     * 参数的数据类型 只会包含数据类型信息
     */
    private String dataType;

    /**
     * 对于字符串型的参数，以字符为单位的最大长度
     */
    private Long characterMaximumLength;

    /**
     * 对于字符串型的参数，以字节为单位的最大长度
     */
    private Long characterOctetLength;

    /**
     * 对于num类型的参数，为num的精度
     */
    private Long numericPrecision;

    /**
     * 对于num类型的参数为num的scale
     */
    private Long numericScale;

    /**
     * 对于日期类型的参数，为分数秒的精度
     */
    private Long datetimePrecision;

    /**
     * 对于字符串类型的参数，为字符集名称
     */
    private String characterSetName;

    /**
     * 对于字符串类型的参数，为字符集排序规则名称
     */
    private String collationName;

    /**
     * 参数的数据类型，可能还包含其他信息，例如精度和长度等
     */
    private String dtdIdentifier;

    /**
     * routine的类型，PROCEDURE 代表存储过程，FUNCTION 代表函数
     */
    private String routineType;


    public String getSpecificCatalog() {
        return specificCatalog;
    }

    public void setSpecificCatalog(String specificCatalog) {
        this.specificCatalog = specificCatalog;
    }

    public String getSpecificSchema() {
        return specificSchema;
    }

    public void setSpecificSchema(String specificSchema) {
        this.specificSchema = specificSchema;
    }

    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    public Long getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Long ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getParameterMode() {
        return parameterMode;
    }

    public void setParameterMode(String parameterMode) {
        this.parameterMode = parameterMode;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Long getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(Long characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public Long getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(Long characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public Long getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Long numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public Long getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(Long numericScale) {
        this.numericScale = numericScale;
    }

    public Long getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(Long datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getDtdIdentifier() {
        return dtdIdentifier;
    }

    public void setDtdIdentifier(String dtdIdentifier) {
        this.dtdIdentifier = dtdIdentifier;
    }

    public String getRoutineType() {
        return routineType;
    }

    public void setRoutineType(String routineType) {
        this.routineType = routineType;
    }
}
