package indi.uhyils.util.kpro.convertor.mysql;


import indi.uhyils.util.kpro.TypeConvertor;

/**
 * mysql的类型转换器
 *
 * @author 商轶龙 <247452312@qq.com>
 * @copyright Copyright 2017-2027 商轶龙
 * @license ZPL (http://zpl.pub/v12)
 * @date 文件创建日期 2018-09-17 17:24
 */
public class MysqlTypeConvertor implements TypeConvertor {

    @Override
    public String databaseType2JavaType(String databaseType) {
        if (databaseType == null) {
            return null;
        }
        if ("bigint".equalsIgnoreCase(databaseType) ||
            "int unsigned".equalsIgnoreCase(databaseType)) {
            return "Long";
        }
        if ("varchar".equalsIgnoreCase(databaseType) ||
            "char".equalsIgnoreCase(databaseType) ||
            "MEDIUMTEXT".equalsIgnoreCase(databaseType) ||
            "text".equalsIgnoreCase(databaseType)) {
            return "String";
        }
        if ("int".equalsIgnoreCase(databaseType) ||
            "tinyint".equalsIgnoreCase(databaseType) ||
            "smallint".equalsIgnoreCase(databaseType) ||
            "integer".equalsIgnoreCase(databaseType) ||
            databaseType.contains("INT")) {
            return "Integer";
        }
        if ("double".equalsIgnoreCase(databaseType)) {
            return "Double";
        }
        if ("float".equalsIgnoreCase(databaseType)) {
            return "Float";
        }
        if ("datetime".equalsIgnoreCase(databaseType)) {
            return "Date";
        }
        if ("text".equalsIgnoreCase(databaseType)) {
            return "String";
        }
        if ("clob".equalsIgnoreCase(databaseType)) {
            return "java.sql.Clob";
        }
        if ("blob".equalsIgnoreCase(databaseType)) {
            return "java.sql.Blob";
        }

        if ("date".equalsIgnoreCase(databaseType)) {
            return "Date";
        }
        if ("time".equalsIgnoreCase(databaseType)) {
            return "Date";
        }
        if ("timestamp".equalsIgnoreCase(databaseType)) {
            return "Date";
        }
        if ("boolean".equalsIgnoreCase(databaseType) ||
            "bit".equalsIgnoreCase(databaseType)) {
            return "Boolean";
        }
        return null;

    }

    @Override
    public String javaType2DatabaseType(String javaType) {

        return null;
    }

}
