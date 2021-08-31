package indi.uhyils.mq.util;


import indi.uhyils.mq.pojo.mqinfo.JvmStartInfoEvent;
import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfoEvent;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

/**
 * Jvm工具
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月18日 12时01分
 */
public class JvmUtil {

    public static JvmStartInfoEvent getJvmStartInfo(JvmUniqueMark jvmUniqueMark) {
        JvmStartInfoEvent jvmStartInfo = new JvmStartInfoEvent();

        List<JvmStatusInfoEvent> statusInfos = JvmStartInfoEvent.getStatusInfos();
        statusInfos.add(getJvmStatusInfo(jvmUniqueMark));
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonUsage = memoryBean.getNonHeapMemoryUsage();

        double heapTotalMem = usage.getMax() / 1024.0 / 1024;
        jvmStartInfo.setHeapTotalMem(heapTotalMem);
        jvmStartInfo.setHeapInitMem(usage.getInit() / 1024.0 / 1024);
        double noHeapTotalMem = nonUsage.getMax() / 1024.0 / 1024;
        jvmStartInfo.setNoHeapTotalMem(noHeapTotalMem);
        jvmStartInfo.setNoHeapInitMem(nonUsage.getInit() / 1024.0 / 1024);
        jvmStartInfo.setJvmTotalMem(heapTotalMem + noHeapTotalMem);
        jvmStartInfo.setJvmUniqueMark(jvmUniqueMark);
        jvmStartInfo.setJvmStatusInfos(statusInfos);
        return jvmStartInfo;
    }

    public static JvmStatusInfoEvent getJvmStatusInfo(JvmUniqueMark jvmUniqueMark) {
        JvmStatusInfoEvent jvmStatusInfo = new JvmStatusInfoEvent();
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonUsage = memoryBean.getNonHeapMemoryUsage();

        double heapUseMem = usage.getUsed() / 1024.0 / 1024;
        jvmStatusInfo.setHeapUseMem(heapUseMem);
        double noHeapUseMem = nonUsage.getUsed() / 1024.0 / 1024;
        jvmStatusInfo.setNoHeapUseMem(noHeapUseMem);
        jvmStatusInfo.setTotalUseMem(heapUseMem + noHeapUseMem);
        jvmStatusInfo.setJvmUniqueMark(jvmUniqueMark);
        jvmStatusInfo.setTime(System.currentTimeMillis());
        return jvmStatusInfo;
    }

}
