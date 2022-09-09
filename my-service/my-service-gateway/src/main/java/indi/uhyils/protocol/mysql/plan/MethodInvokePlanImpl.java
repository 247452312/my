package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.SQLExpr;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.pojo.plan.MethodInvokePlan;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时33分
 */
public class MethodInvokePlanImpl extends MethodInvokePlan {

    public MethodInvokePlanImpl(Map<String, String> headers, String methodName, List<SQLExpr> arguments) {
        super(headers, methodName, arguments);
    }

    @Override
    public NodeInvokeResult invoke() {
        return new NodeInvokeResult();
    }
}
