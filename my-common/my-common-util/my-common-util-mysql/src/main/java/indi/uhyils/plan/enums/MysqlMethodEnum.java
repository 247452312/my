package indi.uhyils.plan.enums;

import com.alibaba.druid.sql.ast.SQLExpr;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * mysql 方法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年04月25日 16时45分
 */
public enum MysqlMethodEnum {
    /**
     * count
     */
    COUNT("count", 1, Long.class, false, (parentInvokeResult, arguments, fieldName) -> {
        Asserts.assertTrue(arguments.size() == 1, "mysql语句中方法使用错误,count入参只能为一个");
        String countParam = arguments.get(0).toString();
        Asserts.assertTrue(StringUtil.isNotEmpty(countParam), "mysql语句中方法使用错误,count入参不能为空白");

        if (StringUtil.equalsIgnoreCase(countParam, "*")) {
            // todo 这里需要添加 sql语句中有group的情况
            int size = parentInvokeResult.getResult().size();
            List<Map<String, Object>> maps = new ArrayList<>();
            Map<String, Object> e = new HashMap<>();
            e.put(fieldName, size);
            maps.add(e);
            return maps;
        } else if (countParam.contains("=")) {
            Asserts.throwException("暂不支持count方法中使用等号来判断");
            return null;
        } else {
            // count中为字段名称
            List<Map<String, Object>> result = parentInvokeResult.getResult();
            long size = result.stream().filter(t -> t.containsKey(fieldName) && t.get(fieldName) != null).count();
            List<Map<String, Object>> maps = new ArrayList<>();
            Map<String, Object> e = new HashMap<>();
            e.put(fieldName, size);
            maps.add(e);
            return maps;
        }
    }),

    /**
     * concat
     */
    CONCAT("concat", -1, String.class, true, (parentInvokeResult, arguments, fieldName) -> {
        List<Map<String, Object>> parentResult = parentInvokeResult.getResult();
        List<Map<String, Object>> result = new ArrayList<>();
        // 遍历每一行
        for (Map<String, Object> lineResult : parentResult) {
            StringBuilder sb = new StringBuilder();
            for (SQLExpr argument : arguments) {
                // todo 这里需要处理argument 如果argument有前缀之类的(a.name)就需要单独处理

                Object o = lineResult.get(argument.toString());
                sb.append(o);
            }
            Map<String, Object> e = new HashMap<>();
            e.put(fieldName, sb.toString());
            result.add(e);
        }
        return result;
    }),
    SUM("sum", 1, Long.class, false, (parentInvokeResult, arguments, fieldName) -> {
        Asserts.assertTrue(arguments.size() == 1, "mysql语句中方法使用错误,sum入参只能为一个");
        String countParam = arguments.get(0).toString();
        Asserts.assertTrue(StringUtil.isNotEmpty(countParam), "mysql语句中方法使用错误,sum入参不能为空白");

        if (StringUtil.equalsIgnoreCase(countParam, "*")) {
            Asserts.throwException("mysql语句中方法使用错误,sum入参不能为*");
            return null;
        } else if (countParam.contains("=")) {
            Asserts.throwException("暂不支持sum方法中使用等号来判断");
            return null;
        } else {
            // count中为字段名称
            List<Map<String, Object>> result = parentInvokeResult.getResult();
            long sum = result.stream().filter(t -> t.containsKey(fieldName) && t.get(fieldName) != null).mapToLong(t -> (long) t.get(fieldName)).sum();
            List<Map<String, Object>> maps = new ArrayList<>();
            Map<String, Object> item = new HashMap<>();
            item.put(fieldName, sum);
            maps.add(item);
            return maps;
        }
    }),
    GROUP_CONCAT("group_concat", 1, String.class, false, (parentInvokeResult, arguments, fieldName) -> {
        List<Map<String, Object>> parentResult = parentInvokeResult.getResult();
        List<Map<String, Object>> result = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        // 遍历每一行
        for (Map<String, Object> lineResult : parentResult) {
            for (SQLExpr argument : arguments) {
                // todo 这里需要处理argument 如果argument有前缀之类的(a.name)就需要单独处理

                Object o = lineResult.get(argument.toString());
                sb.append(o);
            }
        }
        Map<String, Object> e = new HashMap<>();
        e.put(fieldName, sb.toString());
        result.add(e);
        return result;
    });


    /**
     * 方法名称
     */
    private final String name;

    /**
     * 方法入参个数 如果是可变参数,则值为-1
     */
    private final Integer paramCount;

    /**
     * 结果类型
     */
    private final Class<?> resultType;

    /**
     * 解析结果的过程
     */
    private final MakeResultFunction function;

    /**
     * 每行都可以得到一个结果
     */
    private final Boolean singleLine;


    MysqlMethodEnum(String name, Integer paramCount, Class<?> resultType, Boolean singleLine, MakeResultFunction function) {
        this.name = name;
        this.paramCount = paramCount;
        this.resultType = resultType;
        this.singleLine = singleLine;
        this.function = function;
    }

    /**
     * 解析mysql方法
     *
     * @param name       方法名称
     * @param paramCount 方法入参个数
     *
     * @return
     */
    @NotNull
    public static MysqlMethodEnum parse(String name, Integer paramCount) {
        for (MysqlMethodEnum value : values()) {
            if (StringUtil.equalsIgnoreCase(name, value.name) && (value.paramCount == -1 || Objects.equals(paramCount, value.paramCount))) {
                return value;
            }
        }
        Asserts.throwException("未知的mysql方法名称,name:{},参数个数:{}", name, paramCount.toString());
        return null;
    }

    public Boolean getSingleLine() {
        return singleLine;
    }

    /**
     * 解析为结果字段
     *
     * @return
     */
    @NotNull
    public FieldInfo makeFieldInfo(String dbName, String tableName, String tableRealName, Integer index, String fieldName) {
        return FieldTypeEnum.makeFieldInfo(dbName, tableName, tableRealName, resultType, index, fieldName);
    }

    /**
     * 解析为结果
     *
     * @param parentInvokeResult 上一个执行计划的结果
     * @param arguments          入参
     * @param fieldName
     *
     * @return
     */
    public List<Map<String, Object>> makeResult(NodeInvokeResult parentInvokeResult, List<SQLExpr> arguments, String fieldName) {
        return function.makeResult(parentInvokeResult, arguments, fieldName);
    }


    @FunctionalInterface
    interface MakeResultFunction {

        /**
         * 解析结果
         *
         * @param parentInvokeResult 上一个执行计划的结果
         * @param arguments          入参
         *
         * @return
         */
        List<Map<String, Object>> makeResult(NodeInvokeResult parentInvokeResult, List<SQLExpr> arguments, String fieldName);
    }
}
