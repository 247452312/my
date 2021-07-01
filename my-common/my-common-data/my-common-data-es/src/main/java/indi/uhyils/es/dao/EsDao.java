package indi.uhyils.es.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月11日 13时51分
 */
public interface EsDao {


    /**
     * 创建index
     *
     * @param index 索引名称
     * @return
     * @throws IOException
     */
    Boolean createIndex(String index) throws IOException;

    /**
     * 插入
     *
     * @param index  数据库名
     * @param type   表名
     * @param id     主键id
     * @param source 内容
     * @return 是否成功
     */
    Boolean insert(String index, String type, String id, String source) throws IOException;

    /**
     * 插入
     *
     * @param index 数据库名
     * @param type  表名
     * @param id    主键id
     * @param obj   内容
     * @return 是否成功
     */
    Boolean insert(String index, String type, String id, Object obj) throws IOException;

    /**
     * 插入
     *
     * @param index 数据库名
     * @param type  表名
     * @param id    主键id
     * @param bytes 内容
     * @return 是否成功
     */
    Boolean insert(String index, String type, String id, byte[] bytes) throws IOException;

    /**
     * 插入
     *
     * @param index 数据库名
     * @param type  表名
     * @param id    主键id
     * @param map   内容
     * @return 是否成功
     */
    Boolean insert(String index, String type, String id, HashMap<String, Object> map) throws IOException;


    /**
     * 删除
     *
     * @param index 数据库名
     * @param type  表名
     * @param id    主键id
     * @return 是否成功
     */
    Boolean delete(String index, String type, String id) throws IOException;

    /**
     * 获取map
     *
     * @param index 数据库名
     * @param type  表名
     * @param id    主键id
     * @return
     */
    Map<String, Object> getMapById(String index, String type, String id) throws IOException;

    /**
     * 获取对象
     *
     * @param index 数据库名
     * @param type  表名
     * @param id    主键id
     * @return
     */
    <T> T getObjById(String index, String type, String id, Class<T> clazz) throws IOException;

    /**
     * 获取String
     *
     * @param index 数据库名
     * @param type  表名
     * @param id    主键id
     * @return
     */
    String getById(String index, String type, String id) throws IOException;

    /**
     * 获取String
     *
     * @param index 数据库名
     * @param type  表名
     * @param id    主键id
     * @return
     */
    byte[] getByteById(String index, String type, String id) throws IOException;


}
