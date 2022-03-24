package indi.uhyils.pojo.entity.plan;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.DTO.FieldInfo;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月24日 14时22分
 */
public interface MysqlPlan {

    /**
     * 执行执行计划
     *
     * @param resultMap 入参集
     *
     * @return
     */
    JSONArray invoke(Map<Long, JSONArray> resultMap);

    /**
     * 执行计划出参(对应节点出参)
     */
    List<FieldInfo> colInfos();

    /**
     * 执行计划的顺序
     *
     * @return
     */
    Long index();
}
