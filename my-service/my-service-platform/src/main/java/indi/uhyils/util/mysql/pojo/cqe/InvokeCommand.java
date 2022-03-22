package indi.uhyils.util.mysql.pojo.cqe;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年11月26日 08时45分
 */
public class InvokeCommand extends AbstractCommand {

    /**
     * 入参
     */
    private Map<String, Object> params;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 要返回的出餐
     */
    private List<String> selectList;

    /**
     * 调用方id
     */
    private Long consumerId;


    /**
     * 创建
     *
     * @param params     入参
     * @param tableName  表名称(节点名称)
     * @param selectList 查询出参
     *
     * @return
     */
    public static InvokeCommand build(Map<String, Object> params, String tableName, List<String> selectList, Long consumerId) {
        InvokeCommand build = new InvokeCommand();
        build.setParams(params);
        build.setTableName(tableName);
        build.setSelectList(selectList);
        build.setConsumerId(consumerId);
        return build;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<String> selectList) {
        this.selectList = selectList;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }
}
