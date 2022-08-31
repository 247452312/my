package indi.uhyils.protocol.mysql.plan;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.enums.InvokeTypeEnum;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;
import indi.uhyils.plan.result.MysqlPlanResult;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.pojo.cqe.InvokeCommandBuilder;
import indi.uhyils.pojo.dto.FieldInfoDTO;
import indi.uhyils.pojo.dto.response.GetInterfaceInfoResponse;
import indi.uhyils.service.GatewaySdkService;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 简单sql执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时31分
 */
public class BlockQuerySelectSqlPlanImpl extends BlockQuerySelectSqlPlan {

    /**
     * 节点
     */
    private GatewaySdkService gatewaySdkService;


    public BlockQuerySelectSqlPlanImpl(List<MysqlPlan> mysqlPlan, SqlTableSourceBinaryTree froms, Map<String, String> headers, Map<String, Object> params) {
        super(mysqlPlan, froms, headers, params);
        this.gatewaySdkService = SpringUtil.getBean(GatewaySdkService.class);
    }

    @Override
    public MysqlPlanResult invoke() {
        InvokeCommandBuilder invokeCommandBuilder = new InvokeCommandBuilder();
        invokeCommandBuilder.setType(InvokeTypeEnum.MYSQL.getCode());
        invokeCommandBuilder.addArgs(params);
        invokeCommandBuilder.addHeader(headers);
        invokeCommandBuilder.addPath("");
        final InvokeCommand build = invokeCommandBuilder.build();
        final JSONArray jsonArray = gatewaySdkService.invokeNode(build);
        final GetInterfaceInfoResponse interfaceInfo = gatewaySdkService.getInterfaceInfo(build);
        final ArrayList<FieldInfoDTO> fieldInfoDTOS = new ArrayList<>(interfaceInfo.getFieldInfoMap().values());
        List<FieldInfo> fieldInfos = new ArrayList<>(fieldInfoDTOS.size());
        for (int i = 0; i < fieldInfoDTOS.size(); i++) {
            final FieldInfoDTO fieldInfoDTO = fieldInfoDTOS.get(i);
            final FieldInfo fieldInfo = new FieldInfo(fieldInfoDTO.getDatabase(), fieldInfoDTO.getRealTable(), fieldInfoDTO.getRealTable(), fieldInfoDTO.getName(), fieldInfoDTO.getName(), 0, i, FieldTypeEnum.parse(fieldInfoDTO.getType()), (short) 0, fieldInfoDTO.getDecimals().byteValue(), null);
            fieldInfos.add(fieldInfo);
        }
        return new MysqlPlanResult(jsonArray, fieldInfos);
    }
}
