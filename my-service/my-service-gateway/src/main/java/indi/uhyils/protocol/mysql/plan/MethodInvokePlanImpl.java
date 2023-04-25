package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.SQLExpr;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.enums.MysqlMethodEnum;
import indi.uhyils.plan.pojo.plan.MethodInvokePlan;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时33分
 */
public class MethodInvokePlanImpl extends MethodInvokePlan {

    public MethodInvokePlanImpl(Map<String, String> headers, Integer index, String methodName, List<SQLExpr> arguments) {
        super(headers, index, methodName, arguments);
    }

    @Override
    public NodeInvokeResult invoke() {
        MysqlMethodEnum parse = MysqlMethodEnum.parse(methodName, arguments.size());
        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult();
        String fieldName = toFieldName();
        nodeInvokeResult.setFieldInfos(Collections.singletonList(parse.makeFieldInfo(this.index, fieldName)));
        nodeInvokeResult.setResult(parse.makeResult(lastNodeInvokeResult, arguments, fieldName));
        return nodeInvokeResult;
    }

    private String toFieldName() {
        String collect = arguments.stream().map(Object::toString).collect(Collectors.joining(","));
        return MessageFormatter.arrayFormat("{}({})", new Object[]{methodName, collect}).getMessage();
    }
}
