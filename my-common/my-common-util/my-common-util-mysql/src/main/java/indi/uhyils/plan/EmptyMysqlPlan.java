package indi.uhyils.plan;


import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import java.util.List;
import java.util.Map;

/**
 * 空白执行计划,什么都不做
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月01日 08时58分
 */
public class EmptyMysqlPlan extends AbstractMysqlSqlPlan {

    public EmptyMysqlPlan(List<MysqlPlan> lastPlan, Map<String, String> headers) {
        super(lastPlan, null, headers, null);
    }

    @Override
    public NodeInvokeResult invoke() {
        return null;
    }
}
