package indi.uhyils.factory;

import indi.uhyils.log.MyTraceIdContext;
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


    public static TraceInfoEntity createByText(String msg) {
        TraceInfoEntity traceInfoEntity = new TraceInfoEntity();
        int firstPipeSymbol = msg.indexOf(MyTraceIdContext.PIPE_SYMBOL);
        if (firstPipeSymbol == -1) {
            throw new RuntimeException("no | ");
        }
        String[] split = msg.split("\\|");
        String loggerName = split[0];
        traceInfoEntity.setLogName(loggerName);
        String level = split[1];
        traceInfoEntity.setLevel(level);

        switch (loggerName) {
            case "link_log":
                linkLogInfoParse(traceInfoEntity, split);
                StringBuilder sb = new StringBuilder();
                for (int i = 9; i < split.length - 1; i++) {
                    sb.append(split[i]);
                    sb.append(MyTraceIdContext.PIPE_SYMBOL);
                }
                String hash = split[split.length - 1];
                traceInfoEntity.setHashCode(hash);
                break;
            case "sql_log":
                sqlTraceInfoParse(traceInfoEntity, split);
                break;
            default:
                normalTraceInfoParse(traceInfoEntity, split);
                break;
        }
        return traceInfoEntity;
    }

    /**
     * 正常日志解析
     *
     * @param traceInfoEntity
     * @param split
     */
    private static void normalTraceInfoParse(TraceInfoEntity traceInfoEntity, String[] split) {
        String traceId = split[2];
        String rpcId = split[3];

        String startTime = split[4];
        String info = split[5];
        traceInfoEntity.setTraceId(Long.valueOf(traceId));
        traceInfoEntity.setRpcId(rpcId);
        traceInfoEntity.setStartTime(Long.valueOf(startTime));
        traceInfoEntity.setOther(info);
    }

    /**
     * sql日志解析
     *
     * @param traceInfoEntity
     * @param split
     */
    private static void sqlTraceInfoParse(TraceInfoEntity traceInfoEntity, String[] split) {
        String hash = split[2];
        String traceId = split[3];
        String useTime = split[4];
        String sql = split[5];
        traceInfoEntity.setTraceId(Long.valueOf(traceId));
        traceInfoEntity.setHashCode(hash);
        traceInfoEntity.setUseTime(Long.valueOf(useTime));
        traceInfoEntity.setOther(sql);
    }

    /**
     * trace日志
     *
     * @param traceInfoEntity
     * @param split
     */
    private static void linkLogInfoParse(TraceInfoEntity traceInfoEntity, String[] split) {
        String traceId = split[2];
        traceInfoEntity.setTraceId(Long.valueOf(traceId));
        String startTime = split[3];
        traceInfoEntity.setStartTime(Long.valueOf(startTime));
        String type = split[4];
        traceInfoEntity.setLogType(Integer.valueOf(type));
        String rpcId = split[5];
        traceInfoEntity.setRpcId(rpcId);
        String threadName = split[6];
        traceInfoEntity.setThreadName(threadName);
        String applicationName = split[7];
        traceInfoEntity.setProjectName(applicationName);
        String useTime = split[8];
        traceInfoEntity.setUseTime(Long.valueOf(useTime));
    }

    public static Map<Long, OneTraceLink> createByTraceLink(List<TraceInfoEntity> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
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
