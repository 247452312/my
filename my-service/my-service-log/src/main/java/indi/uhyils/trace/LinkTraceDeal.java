package indi.uhyils.trace;

import indi.uhyils.log.MyTraceIdContext;
import indi.uhyils.pojo.model.TraceInfoEntity;
import indi.uhyils.pojo.model.base.BaseDoEntity;


/**
 * 日志解析
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时41分
 */
public class LinkTraceDeal extends AbstractTraceDeal {

    @Override
    protected BaseDoEntity getTargetEntity(String[] split) {
        if (split.length < 10) {
            throw new RuntimeException("错误");
        }
        String loggerName = split[0];
        String logLevel = split[1];

        String traceId = split[2];
        String startTime = split[3];
        String linkType = split[4];
        String rpcId = split[5];
        String threadName = split[6];
        String projectName = split[7];
        String useTime = split[8];
        String hashCode = split[9];
        StringBuilder sb = new StringBuilder();
        for (int i = 10; i < split.length; i++) {
            sb.append(split[i]);
            sb.append(MyTraceIdContext.PIPE_SYMBOL);
        }

        TraceInfoEntity entity = new TraceInfoEntity();
        entity.setLoggerName(loggerName);
        entity.setLevel(logLevel);
        entity.setTraceId(Long.valueOf(traceId));
        entity.setStartTime(Long.valueOf(startTime));
        entity.setLogType(Integer.valueOf(linkType));
        entity.setRpcId(rpcId);
        entity.setThreadName(threadName);
        entity.setProjectName(projectName);
        entity.setUseTime(Long.valueOf(useTime));
        entity.setHashCode(hashCode);
        entity.setOther(sb.toString());
        return entity;

    }
}
