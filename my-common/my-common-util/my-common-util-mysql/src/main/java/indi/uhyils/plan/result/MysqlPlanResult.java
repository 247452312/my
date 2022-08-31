package indi.uhyils.plan.result;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import java.util.List;

/**
 * 执行计划结果
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 09时04分
 */
public class MysqlPlanResult {

    private final JSONArray result;

    private final List<FieldInfo> colInfos;

    public MysqlPlanResult(JSONArray result, List<FieldInfo> colInfos) {
        this.result = result;
        this.colInfos = colInfos;
    }


    /**
     * 获取执行计划结果
     *
     * @return
     */
    public JSONArray result() {
        return result;
    }


    /**
     * 列信息,要返回的
     *
     * @return
     */
    public List<FieldInfo> colInfos() {
        return colInfos;
    }

}
