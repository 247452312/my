package indi.uhyils.protocol.mysql.pojo.cqe;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import indi.uhyils.protocol.mysql.plan.MysqlPlan;
import java.util.Map;

/**
 * 执行command
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年11月25日 10时57分
 */
public class InvokePlanCommand extends AbstractCommand {

    /**
     * 实际入参
     */
    private Map<Long, JSONArray> params;

    /**
     * 执行计划
     */
    private MysqlPlan plan;

    public static InvokePlanCommand build(MysqlPlan plan, Map<Long, JSONArray> params) {
        InvokePlanCommand invokePlanCommand = new InvokePlanCommand();
        invokePlanCommand.plan = plan;
        invokePlanCommand.params = params;
        return invokePlanCommand;
    }

    public Map<Long, JSONArray> getParams() {
        return params;
    }

    public void setParams(Map<Long, JSONArray> params) {
        this.params = params;
    }

    public MysqlPlan getPlan() {
        return plan;
    }

    public void setPlan(MysqlPlan plan) {
        this.plan = plan;
    }
}
