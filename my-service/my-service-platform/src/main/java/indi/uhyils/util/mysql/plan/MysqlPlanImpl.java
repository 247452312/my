package indi.uhyils.util.mysql.plan;

import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.DTO.FieldInfo;
import indi.uhyils.protocol.mysql.history.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.history.handler.MysqlHandlerImpl;
import indi.uhyils.protocol.mysql.history.service.MysqlService;
import indi.uhyils.util.SpringUtil;
import indi.uhyils.util.StringUtil;
import indi.uhyils.util.mysql.pojo.cqe.InvokeCommand;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年11月25日 09时51分
 */
public class MysqlPlanImpl implements MysqlPlan {

    /**
     * 系统参数
     */
    private final MysqlHandler handler;

    /**
     * 执行计划id
     */
    private final Long index;

    /**
     * 执行计划指向的表
     */
    private SQLTableSource table;

    /**
     * 执行计划指向的数据
     */
    private JSONArray tableSource;

    /**
     * 执行计划的入参名称
     */
    private List<String> paramNames;

    /**
     * 执行计划要查询的列
     */
    private List<String> selectList;

    /**
     * 列信息
     */
    private List<FieldInfo> fieldInfos;

    /**
     * 实际的入参,可以提前指定 key为执行计划id,如果key为-1 则代表实际入参
     */
    private Map<Long, JSONArray> params = new HashMap<>();

    /**
     * 实际执行类
     */
    private MysqlService mysqlExtension;

    public MysqlPlanImpl(Long index, MysqlHandler handler) {
        this.index = index;
        this.handler = handler;
        this.mysqlExtension = SpringUtil.getBean(MysqlService.class);
    }

    public MysqlPlanImpl() {
        this(MysqlHandlerImpl.MYSQL_HANDLER.get().getAndAddPlanIndex(1), MysqlHandlerImpl.MYSQL_HANDLER.get());
    }


    @Override
    public Long index() {
        return index;
    }

    @Override
    public List<FieldInfo> colInfos() {
        return fieldInfos;
    }

    @Override
    public JSONArray invoke(Map<Long, JSONArray> param) throws Exception {
        if (this.params != null) {
            this.params.putAll(param);
            param = this.params;
        }

        /* todo 此处应该分类型进行执行, 1.不需要调用接口,from中的table被之前的执行计划代替了的 2.需要调用下层接口的 */
        // 填充执行计划中的占位符
        Map<String, Object> resultParams = fillPlanPlaceholder(param);
        ServiceResult<InvokeResponse> jsonArrayServiceResult = mysqlExtension.invoke(InvokeCommand.build(resultParams, table.toString(), selectList, handler.getUserDTO().getId()));
        InvokeResponse invokeResponse = jsonArrayServiceResult.validationAndGet();
        if (invokeResponse == null) {
            return null;
        } else {
            return invokeResponse.getResult();
        }
    }

    /**
     * 填充执行计划中的占位符
     *
     * @param param
     */
    private Map<String, Object> fillPlanPlaceholder(Map<Long, JSONArray> param) {
        /*1. 处理table, table 如果是&开头, 则后面一定跟一个数字,假定数字为x, 这代表此查询查的是第x个执行计划的返回*/
        String tableName = table.toString();
        if (StringUtil.isNotEmpty(tableName) && tableName.startsWith("&")) {
            String substring = tableName.substring(1);
            long l = Long.parseLong(substring);
            JSONArray jsonArray = param.get(l);
            this.tableSource = jsonArray;
        }
        /*注,因为子查询都会被转为连表查询.所以这里不需要转换selectList*/
        /*2. 处理where*/

        return new HashMap<>(0);
        // todo 填充执行计划中的占位符
    }

    @Override
    public MysqlHandler getMysqlHandler() {
        return handler;
    }

    public void setTable(SQLTableSource table) {
        this.table = table;
    }

    public void setParamNames(List<String> paramNames) {
        this.paramNames = paramNames;
    }

    public void setSelectList(List<String> selectList) {
        this.selectList = selectList;
    }


    public void setParams(Map<Long, JSONArray> params) {
        for (Entry<Long, JSONArray> entry : params.entrySet()) {
            JSONArray orDefault = this.params.getOrDefault(entry.getKey(), new JSONArray());
            orDefault.addAll(entry.getValue());
            this.params.put(entry.getKey(), orDefault);
        }
    }

    public void setParams(JSONArray params) {
        HashMap<Long, JSONArray> noPlanParamMap = new HashMap<>(1);
        noPlanParamMap.put(-1L, params);
        setParams(noPlanParamMap);

    }

}
