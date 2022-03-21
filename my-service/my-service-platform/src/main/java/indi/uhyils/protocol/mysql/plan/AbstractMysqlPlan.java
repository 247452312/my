package indi.uhyils.protocol.mysql.plan;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import java.util.Map;

/**
 * 执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月03日 08时48分
 */
public abstract class AbstractMysqlPlan implements MysqlPlan {


    /**
     * 子执行计划 key ->执行计划顺序
     */
    private Map<Integer, MysqlPlan> subtrees;

    /**
     * mysql服务器信息
     */
    private MysqlHandler handler;

    public AbstractMysqlPlan(MysqlHandler handler) {
        this.handler = handler;
    }

    @Override
    public JSONArray invoke(Map<Long, JSONArray> param) throws Exception {

        return null;
    }

    /**
     * 执行执行计划
     *
     * @param param
     *
     * @return
     */
    protected abstract JSONArray doInvoke(Map<Long, JSONArray> param);


}
