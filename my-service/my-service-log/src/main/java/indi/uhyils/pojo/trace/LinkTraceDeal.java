package indi.uhyils.pojo.trace;

import indi.uhyils.context.MyTraceIdContext;
import indi.uhyils.pojo.DO.TraceInfoDO;
import indi.uhyils.pojo.DTO.TraceInfoDTO;
import indi.uhyils.pojo.entity.TraceDetail;
import indi.uhyils.repository.TraceDetailRepository;


/**
 * 日志解析
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时41分
 */
public class LinkTraceDeal extends AbstractTraceDeal<TraceInfoDTO> {


    @Override
    protected TraceInfoDTO getTargetEntity(String[] split) {
        if (split.length < 11) {
            throw new RuntimeException("错误");
        }
        String loggerName = split[0];
        String logLevel = split[1];

        String traceId = split[2];
        String startTime = split[3];
        String linkType = split[4];
        String ip = split[5];
        String rpcId = split[6];
        String threadName = split[7];
        String projectName = split[8];
        String useTime = split[9];
        String hashCode = split[10];
        StringBuilder sb = new StringBuilder();
        for (int i = 10; i < split.length; i++) {
            sb.append(split[i]);
            sb.append(MyTraceIdContext.PIPE_SYMBOL);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        TraceInfoDTO entity = new TraceInfoDTO();
        entity.setLoggerName(loggerName);
        entity.setLevel(logLevel);
        entity.setTraceId(Long.valueOf(traceId));
        entity.setStartTime(Long.valueOf(startTime));
        entity.setLogType(Integer.valueOf(linkType));
        entity.setIp(ip);
        entity.setRpcId(rpcId);
        entity.setThreadName(threadName);
        entity.setProjectName(projectName);
        entity.setUseTime(Long.valueOf(useTime));
        entity.setHashCode(hashCode);
        entity.setOther(sb.toString());
        return entity;

    }
}
