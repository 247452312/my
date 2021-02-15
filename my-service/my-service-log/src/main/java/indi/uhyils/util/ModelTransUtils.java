package indi.uhyils.util;

import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmStartInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.pojo.model.LogMonitorEntity;
import indi.uhyils.pojo.model.LogMonitorInterfaceCallEntity;
import indi.uhyils.pojo.model.LogMonitorJvmStatusEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * model转换
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 15时52分
 */
public class ModelTransUtils {

    public static LogMonitorEntity transJvmStartInfoToMonitorDO(JvmStartInfo jvmStartInfo) {
        LogMonitorEntity logMonitorEntity = new LogMonitorEntity();
        JvmUniqueMark jvmUniqueMark = jvmStartInfo.getJvmUniqueMark();
        logMonitorEntity.setIp(jvmUniqueMark.getIp());
        logMonitorEntity.setServiceName(jvmUniqueMark.getServiceName());
        Long time = jvmUniqueMark.getTime();
        logMonitorEntity.setTime(time);

        //设置假想结束时间=JVM上次发送状态时间+ OUT_TIME*比例系数
        double v = time + RabbitMqContent.OUT_TIME * 60 * 1000 * RabbitMqContent.OUT_TIME_PRO;
        logMonitorEntity.setEndTime(new Double(v).longValue());
        logMonitorEntity.setJvmTotalMem(jvmStartInfo.getJvmTotalMem());
        logMonitorEntity.setHeapTotalMem(jvmStartInfo.getHeapTotalMem());
        logMonitorEntity.setHeapInitMem(jvmStartInfo.getHeapInitMem());
        logMonitorEntity.setNoHeapTotalMem(jvmStartInfo.getNoHeapTotalMem());
        logMonitorEntity.setNoHeapInitMem(jvmStartInfo.getNoHeapInitMem());
        return logMonitorEntity;
    }

    public static LogMonitorJvmStatusEntity transJvmStatusInfoToMonitorJvmStatusDetailDO(JvmStatusInfo jvmStatusInfo, Long fid) {
        LogMonitorJvmStatusEntity logMonitorJvmStatusEntity = new LogMonitorJvmStatusEntity();
        logMonitorJvmStatusEntity.setFid(fid);
        logMonitorJvmStatusEntity.setHeapUseMem(jvmStatusInfo.getHeapUseMem());
        logMonitorJvmStatusEntity.setNoHeapUseMem(jvmStatusInfo.getNoHeapUseMem());
        logMonitorJvmStatusEntity.setTime(jvmStatusInfo.getTime());
        logMonitorJvmStatusEntity.setUseMem(jvmStatusInfo.getTotalUseMem());
        return logMonitorJvmStatusEntity;
    }

    public static List<LogMonitorJvmStatusEntity> transJvmStatusInfosToMonitorJvmStatusDetailDOs(List<JvmStatusInfo> jvmStatusInfos, Long fid) {
        return jvmStatusInfos.stream().map(jvmStatusInfo -> transJvmStatusInfoToMonitorJvmStatusDetailDO(jvmStatusInfo, fid)).collect(Collectors.toList());
    }

    public static LogMonitorInterfaceCallEntity transInterfaceCallInfoToMonitorInterfaceDetailDO(InterfaceCallInfo interfaceCallInfo, Long fid) {
        LogMonitorInterfaceCallEntity logMonitorInterfaceCallEntity = new LogMonitorInterfaceCallEntity();
        logMonitorInterfaceCallEntity.setFid(fid);
        logMonitorInterfaceCallEntity.setInterfaceName(interfaceCallInfo.getInterfaceName());
        logMonitorInterfaceCallEntity.setMethodName(interfaceCallInfo.getMethodName());
        logMonitorInterfaceCallEntity.setRunTime(interfaceCallInfo.getRunTime());
        logMonitorInterfaceCallEntity.setSuccess(interfaceCallInfo.getSuccess());
        logMonitorInterfaceCallEntity.setTime(interfaceCallInfo.getTime());
        logMonitorInterfaceCallEntity.setRequestLength(interfaceCallInfo.getRequestLength());
        logMonitorInterfaceCallEntity.setResponseLength(interfaceCallInfo.getResponseLength());
        return logMonitorInterfaceCallEntity;
    }
}
