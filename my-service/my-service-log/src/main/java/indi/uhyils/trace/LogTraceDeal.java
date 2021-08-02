package indi.uhyils.trace;

import indi.uhyils.pojo.model.TraceLogEntity;
import indi.uhyils.pojo.model.base.BaseDoEntity;


/**
 * 日志解析
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时41分
 */
public class LogTraceDeal extends AbstractTraceDeal {

    @Override
    protected BaseDoEntity getTargetEntity(String[] split) {
        if (split.length != 6) {
            throw new RuntimeException("错误");
        }
        String loggerName = split[0];
        String logLevel = split[1];
        String traceId = split[2];
        String rpcId = split[3];
        String nowTime = split[4];
        String log = split[5];
        TraceLogEntity traceLogEntity = new TraceLogEntity();
        traceLogEntity.setLoggerName(loggerName);
        traceLogEntity.setLogLevel(logLevel);
        traceLogEntity.setTraceId(Long.valueOf(traceId));
        traceLogEntity.setRpcId(rpcId);
        traceLogEntity.setNowTime(Long.valueOf(nowTime));
        traceLogEntity.setLog(log);
        return traceLogEntity;
    }
}
