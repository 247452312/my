package indi.uhyils.pojo.entity.node;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.pojo.entity.type.BaseType;
import indi.uhyils.util.Asserts;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月24日 14时25分
 */
public class Sql implements BaseType {

    /**
     * 可变符号前缀
     */
    private static final String VARIABLE_SYMBOL_PREFIX = "#{";

    /**
     * 后缀
     */
    private static final String VARIABLE_SYMBOL_SUFFIX = "}";

    /**
     * sql本体
     */
    private final String sql;

    /**
     * 替换变量 key为 xxx value为 #{xxx}
     */
    private final Map<String, String> replaceableVariable = new HashMap<>();


    public Sql(String sql) {
        this.sql = sql;
        // 初始化替换变量
        initVariable(sql);
    }

    /**
     * 初始化替换变量
     *
     * @param sql
     */
    private void initVariable(String sql) {
        int index = 0;
        while ((index = sql.indexOf(VARIABLE_SYMBOL_PREFIX, index)) != -1) {
            int endIndex = sql.indexOf(VARIABLE_SYMBOL_SUFFIX, index);
            Asserts.assertTrue(endIndex != -1, "sql变量错误:{}", sql);
            // 符号
            String variable = sql.substring(index, endIndex);
            String key = variable.substring(2, variable.length() - 1);
            replaceableVariable.put(key, variable);
        }
    }

    /**
     * 替换真实的sql
     *
     * @param params
     *
     * @return
     */
    public String replaceRealSql(JSONObject params) {
        if (params == null) {
            return sql;
        }
        String realSql = sql;
        for (String key : params.keySet()) {
            // 如果入参中的参数 sql中不存在替换,则忽略
            if (!replaceableVariable.containsKey(key)) {
                continue;
            }
            // 原始
            String original = replaceableVariable.get(key);
            realSql = realSql.replace(original, params.getString(key));
        }
        return realSql;
    }
}
