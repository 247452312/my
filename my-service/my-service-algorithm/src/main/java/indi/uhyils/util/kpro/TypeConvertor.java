package indi.uhyils.util.kpro;

/**
 * 类型转换器
 *
 * @author 商轶龙 <247452312@qq.com>
 * @copyright Copyright 2017-2027 商轶龙
 * @license ZPL (http://zpl.pub/v12)
 * @date 文件创建日期 2018-09-17 17:24
 */
public interface TypeConvertor {


    String databaseType2JavaType(String databaseType);


    String javaType2DatabaseType(String javaType);
}
