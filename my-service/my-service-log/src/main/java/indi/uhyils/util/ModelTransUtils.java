package indi.uhyils.util;

import indi.uhyils.pojo.model.MonitorDO;
import indi.uhyils.pojo.model.MonitorInterfaceDetailDO;
import indi.uhyils.pojo.model.MonitorJvmStatusDetailDO;
import indi.uhyils.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.pojo.mqinfo.JvmStartInfo;
import indi.uhyils.pojo.mqinfo.JvmStatusInfo;
import indi.uhyils.pojo.mqinfo.JvmUniqueMark;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * model转换
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 15时52分
 */
public class ModelTransUtils {

    /**
     * 假象结束时间->超出时间(分钟)
     */
    public static final Long OUT_TIME = 30L;

    /**
     * 假象结束时间->比例系数
     */
    public static final Double PROP = 1.1;

    public static MonitorDO transJvmStartInfoToMonitorDO(JvmStartInfo jvmStartInfo) {
        MonitorDO monitorDO = new MonitorDO();
        JvmUniqueMark jvmUniqueMark = jvmStartInfo.getJvmUniqueMark();
        monitorDO.setIp(jvmUniqueMark.getIp());
        monitorDO.setServiceName(jvmUniqueMark.getServiceName());
        Long time = jvmUniqueMark.getTime();
        monitorDO.setTime(time);

        //设置假想结束时间=JVM上次发送状态时间+ OUT_TIME*比例系数
        double v = time + OUT_TIME * 60 * 1000 * PROP;
        monitorDO.setEndTime(new Double(v).longValue());
        monitorDO.setJvmTotalMem(jvmStartInfo.getJvmTotalMem());
        monitorDO.setHeapTotalMem(jvmStartInfo.getHeapTotalMem());
        monitorDO.setHeapInitMem(jvmStartInfo.getHeapInitMem());
        monitorDO.setNoHeapTotalMem(jvmStartInfo.getNoHeapTotalMem());
        monitorDO.setNoHeapInitMem(jvmStartInfo.getNoHeapInitMem());
        return monitorDO;
    }

    public static MonitorJvmStatusDetailDO transJvmStatusInfoToMonitorJvmStatusDetailDO(JvmStatusInfo jvmStatusInfo, String fid) {
        MonitorJvmStatusDetailDO monitorJvmStatusDetailDO = new MonitorJvmStatusDetailDO();
        monitorJvmStatusDetailDO.setFid(fid);
        monitorJvmStatusDetailDO.setHeapUseMem(jvmStatusInfo.getHeapUseMem());
        monitorJvmStatusDetailDO.setNoHeapUseMem(jvmStatusInfo.getNoHeapUseMem());
        monitorJvmStatusDetailDO.setTime(jvmStatusInfo.getTime());
        monitorJvmStatusDetailDO.setUseMem(jvmStatusInfo.getTotalUseMem());
        return monitorJvmStatusDetailDO;
    }

    public static List<MonitorJvmStatusDetailDO> transJvmStatusInfosToMonitorJvmStatusDetailDOs(List<JvmStatusInfo> jvmStatusInfos, String fid) {
        return jvmStatusInfos.stream().map(jvmStatusInfo -> transJvmStatusInfoToMonitorJvmStatusDetailDO(jvmStatusInfo, fid)).collect(Collectors.toList());
    }

    public static MonitorInterfaceDetailDO transInterfaceCallInfoToMonitorInterfaceDetailDO(InterfaceCallInfo interfaceCallInfo, String fid) {
        MonitorInterfaceDetailDO monitorInterfaceDetailDO = new MonitorInterfaceDetailDO();
        monitorInterfaceDetailDO.setFid(fid);
        monitorInterfaceDetailDO.setInterfaceName(interfaceCallInfo.getInterfaceName());
        monitorInterfaceDetailDO.setMethodName(interfaceCallInfo.getMethodName());
        monitorInterfaceDetailDO.setRunTime(interfaceCallInfo.getRunTime());
        monitorInterfaceDetailDO.setSuccess(interfaceCallInfo.getSuccess());
        monitorInterfaceDetailDO.setTime(interfaceCallInfo.getTime());
        return monitorInterfaceDetailDO;
    }
}
