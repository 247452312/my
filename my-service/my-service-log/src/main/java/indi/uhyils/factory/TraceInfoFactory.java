package indi.uhyils.factory;

import com.sun.istack.internal.NotNull;
import indi.uhyils.pojo.model.TraceInfoEntity;
import indi.uhyils.pojo.response.trace.OneTraceLink;
import indi.uhyils.pojo.response.trace.OneTraceNode;
import indi.uhyils.util.StringUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月31日 18时55分
 */
public class TraceInfoFactory {


    @NotNull
    public static Map<Long, OneTraceLink> createByTraceLink(List<TraceInfoEntity> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }
        Map<Long, OneTraceLink> result = new HashMap<>();
        list.sort(Comparator.comparingInt(t -> StringUtil.containsCount(t.getRpcId(), '.')));
        for (TraceInfoEntity traceInfoEntity : list) {
            Long traceId = traceInfoEntity.getTraceId();
            if (!result.containsKey(traceId)) {
                OneTraceLink value = OneTraceLink.build(traceId, new ArrayList<>());
                value.setTraceId(traceId);
                result.put(traceId, value);
                continue;
            }
            OneTraceLink oneTraceLink = result.get(traceId);
            List<OneTraceNode> childList = oneTraceLink.getList();
            OneTraceNode thisNode = new OneTraceNode();
            thisNode.setTraceInfoEntity(traceInfoEntity);
            String rpcId = traceInfoEntity.getRpcId();
            OneTraceNode next = null;
            for (OneTraceNode oneTraceNode : childList) {
                if (rpcId.startsWith(oneTraceNode.getTraceInfoEntity().getRpcId())) {
                    next = oneTraceNode;
                    break;
                }
            }
            if (next == null) {
                childList.add(thisNode);
            } else {
                List<OneTraceNode> lastLayerTrace = next.getLastLayerTrace();
                if (lastLayerTrace == null) {
                    lastLayerTrace = new ArrayList<>();
                    next.setLastLayerTrace(lastLayerTrace);
                }
                lastLayerTrace.add(thisNode);
            }
        }
        return result;
    }
}
