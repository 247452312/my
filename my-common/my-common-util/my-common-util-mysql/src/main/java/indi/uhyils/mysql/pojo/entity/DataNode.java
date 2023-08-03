package indi.uhyils.mysql.pojo.entity;

import indi.uhyils.annotation.NotNull;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import java.util.Map;

/**
 * 数据处理节点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月30日 09时10分
 */
public interface DataNode {

    /**
     * 库名称
     */
    @NotNull
    String databaseName();

    /**
     * 表名
     *
     * @return
     */
    @NotNull
    String tableName();

    /**
     * 获取结果集
     *
     * @param header
     * @param params
     *
     * @return
     */
    NodeInvokeResult getResult(Map<String, String> header, Map<String, Object> params);

}
