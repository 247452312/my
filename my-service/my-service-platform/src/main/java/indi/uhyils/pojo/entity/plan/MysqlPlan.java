package indi.uhyils.pojo.entity.plan;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.DTO.FieldInfo;
import indi.uhyils.pojo.entity.node.Node;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.repository.PlatformPublishNodeRepository;
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
     * 执行计划出参(对应节点出参)(实际出参-(select id,name from ...) ,并不是理论出参-(select * from ...))
     */
    List<FieldInfo> colInfos();

    /**
     * 初始化计划的下一个节点
     *
     * @param publishNodeRepository
     * @param internalNodeRepository
     */
    void initNode(PlatformPublishNodeRepository publishNodeRepository, PlatformInternalNodeRepository internalNodeRepository);

    /**
     * 获取对应的节点
     *
     * @return
     */
    Node getNode();
}
