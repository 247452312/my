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
        LogMonitorEntity monitorDO = new LogMonitorEntity();
        JvmUniqueMark jvmUniqueMark = jvmStartInfo.getJvmUniqueMark();
        monitorDO.setIp(jvmUniqueMark.getIp());
        monitorDO.setServiceName(jvmUniqueMark.getServiceName());
        Long time = jvmUniqueMark.getTime();
        monitorDO.setTime(time);

        //设置假想结束时间=JVM上次发送状态时间+ OUT_TIME*比例系数
        double v = time + RabbitMqContent.OUT_TIME * 60 * 1000 * RabbitMqContent.OUT_TIME_PRO;
        monitorDO.setEndTime(new Double(v).longValue());
        monitorDO.setJvmTotalMem(jvmStartInfo.getJvmTotalMem());
        monitorDO.setHeapTotalMem(jvmStartInfo.getHeapTotalMem());
        monitorDO.setHeapInitMem(jvmStartInfo.getHeapInitMem());
        monitorDO.setNoHeapTotalMem(jvmStartInfo.getNoHeapTotalMem());
        monitorDO.setNoHeapInitMem(jvmStartInfo.getNoHeapInitMem());
        return monitorDO;
    }

    public static LogMonitorJvmStatusEntity transJvmStatusInfoToMonitorJvmStatusDetailDO(JvmStatusInfo jvmStatusInfo, Long fid) {
        LogMonitorJvmStatusEntity monitorJvmStatusDetailDO = new LogMonitorJvmStatusEntity();
        monitorJvmStatusDetailDO.setFid(fid);
        monitorJvmStatusDetailDO.setHeapUseMem(jvmStatusInfo.getHeapUseMem());
        monitorJvmStatusDetailDO.setNoHeapUseMem(jvmStatusInfo.getNoHeapUseMem());
        monitorJvmStatusDetailDO.setTime(jvmStatusInfo.getTime());
        monitorJvmStatusDetailDO.setUseMem(jvmStatusInfo.getTotalUseMem());
        return monitorJvmStatusDetailDO;
    }

    public static List<LogMonitorJvmStatusEntity> transJvmStatusInfosToMonitorJvmStatusDetailDOs(List<JvmStatusInfo> jvmStatusInfos, Long fid) {
        return jvmStatusInfos.stream().map(jvmStatusInfo -> transJvmStatusInfoToMonitorJvmStatusDetailDO(jvmStatusInfo, fid)).collect(Collectors.toList());
    }

    public static LogMonitorInterfaceCallEntity transInterfaceCallInfoToMonitorInterfaceDetailDO(InterfaceCallInfo interfaceCallInfo, Long fid) {
        LogMonitorInterfaceCallEntity monitorInterfaceDetailDO = new LogMonitorInterfaceCallEntity();
        monitorInterfaceDetailDO.setFid(fid);
        monitorInterfaceDetailDO.setInterfaceName(interfaceCallInfo.getInterfaceName());
        monitorInterfaceDetailDO.setMethodName(interfaceCallInfo.getMethodName());
        monitorInterfaceDetailDO.setRunTime(interfaceCallInfo.getRunTime());
        monitorInterfaceDetailDO.setSuccess(interfaceCallInfo.getSuccess());
        monitorInterfaceDetailDO.setTime(interfaceCallInfo.getTime());
        return monitorInterfaceDetailDO;
    }
}
