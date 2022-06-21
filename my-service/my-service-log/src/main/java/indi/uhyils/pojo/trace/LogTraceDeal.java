package indi.uhyils.pojo.trace;

import indi.uhyils.context.MyTraceIdContext;
import indi.uhyils.pojo.DTO.TraceLogDTO;


/**
 * 日志解析
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时41分
 */
public class LogTraceDeal extends AbstractTraceDeal<TraceLogDTO> {

    @Override
    protected TraceLogDTO getTargetEntity(String[] split) {
        if (split.length < 6) {
            throw new RuntimeException("错误");
        }
        String loggerName = split[0];
        String logLevel = split[1];
        String traceId = split[2];
        String rpcId = split[3];
        String nowTime = split[4];
        StringBuilder log = new StringBuilder();
        for (int i = 5; i < split.length; i++) {
            log.append(split[i]);
            log.append(MyTraceIdContext.PIPE_SYMBOL);
        }
        TraceLogDTO traceLogEntity = new TraceLogDTO();
        traceLogEntity.setLoggerName(loggerName);
        traceLogEntity.setLogLevel(logLevel);
        traceLogEntity.setTraceId(Long.valueOf(traceId));
        traceLogEntity.setRpcId(rpcId);
        traceLogEntity.setNowTime(Long.valueOf(nowTime));
        traceLogEntity.setLog(log.toString());
        return traceLogEntity;
    }
}
