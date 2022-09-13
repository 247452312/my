package indi.uhyils.mysql.pojo.DTO;

import java.io.Serializable;
import java.util.Date;

/**
 * 存储子程序（存储程序和函数）的信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月13日 08时56分
 */
public class RoutinesInfo implements Serializable {

    /**
     *
     */
    private String specificName;

    /**
     *
     */
    private String routineCatalog;

    /**
     *
     */
    private String routineSchema;

    /**
     *
     */
    private String routineName;

    /**
     *
     */
    private String routineType;

    /**
     *
     */
    private String dataType;

    /**
     *
     */
    private Long characterMaximumLength;

    /**
     *
     */
    private Long characterOctetLength;

    /**
     *
     */
    private Long numericPrecision;

    /**
     *
     */
    private Long numericScale;

    /**
     *
     */
    private Long datetimePrecision;

    /**
     *
     */
    private String characterSetName;

    /**
     *
     */
    private String collationName;

    /**
     *
     */
    private String dtdIdentifier;

    /**
     *
     */
    private String routineBody;

    /**
     *
     */
    private String routineDefinition;

    /**
     *
     */
    private String externalName;

    /**
     *
     */
    private String externalLanguage;

    /**
     *
     */
    private String parameterStyle;

    /**
     *
     */
    private String isDeterministic;

    /**
     *
     */
    private String sqlDataAccess;

    /**
     *
     */
    private String sqlPath;

    /**
     *
     */
    private String securityType;

    /**
     *
     */
    private Date created;

    /**
     *
     */
    private Date lastAltered;

    /**
     *
     */
    private String sqlMode;

    /**
     *
     */
    private String routineComment;

    /**
     *
     */
    private String definer;

    /**
     *
     */
    private String characterSetClient;

    /**
     *
     */
    private String collationConnection;

    /**
     *
     */
    private String databaseCollation;

    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    public String getRoutineCatalog() {
        return routineCatalog;
    }

    public void setRoutineCatalog(String routineCatalog) {
        this.routineCatalog = routineCatalog;
    }

    public String getRoutineSchema() {
        return routineSchema;
    }

    public void setRoutineSchema(String routineSchema) {
        this.routineSchema = routineSchema;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public String getRoutineType() {
        return routineType;
    }

    public void setRoutineType(String routineType) {
        this.routineType = routineType;
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

    public String getRoutineBody() {
        return routineBody;
    }

    public void setRoutineBody(String routineBody) {
        this.routineBody = routineBody;
    }

    public String getRoutineDefinition() {
        return routineDefinition;
    }

    public void setRoutineDefinition(String routineDefinition) {
        this.routineDefinition = routineDefinition;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    public String getExternalLanguage() {
        return externalLanguage;
    }

    public void setExternalLanguage(String externalLanguage) {
        this.externalLanguage = externalLanguage;
    }

    public String getParameterStyle() {
        return parameterStyle;
    }

    public void setParameterStyle(String parameterStyle) {
        this.parameterStyle = parameterStyle;
    }

    public String getIsDeterministic() {
        return isDeterministic;
    }

    public void setIsDeterministic(String isDeterministic) {
        this.isDeterministic = isDeterministic;
    }

    public String getSqlDataAccess() {
        return sqlDataAccess;
    }

    public void setSqlDataAccess(String sqlDataAccess) {
        this.sqlDataAccess = sqlDataAccess;
    }

    public String getSqlPath() {
        return sqlPath;
    }

    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastAltered() {
        return lastAltered;
    }

    public void setLastAltered(Date lastAltered) {
        this.lastAltered = lastAltered;
    }

    public String getSqlMode() {
        return sqlMode;
    }

    public void setSqlMode(String sqlMode) {
        this.sqlMode = sqlMode;
    }

    public String getRoutineComment() {
        return routineComment;
    }

    public void setRoutineComment(String routineComment) {
        this.routineComment = routineComment;
    }

    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    public String getCharacterSetClient() {
        return characterSetClient;
    }

    public void setCharacterSetClient(String characterSetClient) {
        this.characterSetClient = characterSetClient;
    }

    public String getCollationConnection() {
        return collationConnection;
    }

    public void setCollationConnection(String collationConnection) {
        this.collationConnection = collationConnection;
    }

    public String getDatabaseCollation() {
        return databaseCollation;
    }

    public void setDatabaseCollation(String databaseCollation) {
        this.databaseCollation = databaseCollation;
    }
}
