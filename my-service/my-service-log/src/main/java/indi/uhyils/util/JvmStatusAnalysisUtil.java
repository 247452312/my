package indi.uhyils.util;

import indi.uhyils.enum_.ServiceQualityEnum;
import indi.uhyils.pojo.model.LogMonitorEntity;
import indi.uhyils.pojo.model.LogMonitorJvmStatusEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * JVM信息分析工具
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 08时30分
 */
public class JvmStatusAnalysisUtil {


    /**
     * 可分析的最小值
     */
    private static final Integer STATUS_SIZE_MIN = 6;

    /**
     * 分析jvm运行信息 TODO 这个应该移到智能模块中去
     *
     * @param logMonitorEntity 初始内存
     * @param statuses         状态们
     * @return 是否健康
     */
    public static List<ServiceQualityEnum> analysis(LogMonitorEntity logMonitorEntity, List<LogMonitorJvmStatusEntity> statuses) {
        List<ServiceQualityEnum> list = new ArrayList<>();
        if (statuses.size() < STATUS_SIZE_MIN) {
            // 如果状态信息没有超过6个 也就是系统没有运行超过3个小时 没有分析的必要 直接返回正常
            list.add(ServiceQualityEnum.GOOD);
            return list;
        }
        /* 1.如果程序总内存除了刚刚启动以外一直在增加,判断为内存溢出,警告 */
        Boolean memStillUp = true;
        for (int i = 1; i < statuses.size(); i++) {
            if (statuses.get(i).getUseMem() <= statuses.get(i - 1).getUseMem()) {
                memStillUp = Boolean.FALSE;
                break;
            }
        }
        //代表内存一直在溢出
        if (memStillUp) {
            list.add(ServiceQualityEnum.OUT_OF_MEMORY);
        }

        /* 2.如果是程序内存经常(经常理解为超过总发送次数的1/3,下同)超过最大值的80%,或者经常低于最低内存的50%(低于成立的前提是一次高于最低内存的时候都没有) 判断为JVM可调优,警告 */
        // JVM调优->最大值的百分比 阈值
        final double outMaxPro = 0.8;
        // 次数阈值
        int threshold = statuses.size() / 3;
        // 总内存超出阈值次数
        int allMemOutCount = 0;
        // 堆内存超出次数
        int heapMemOutCount = 0;
        // 非堆内存超出次数
        int noHeapOutCount = 0;

        // JVM调优->最大值的百分比 阈值
        final double lowMinPro = 0.5;
        // 总内存超出阈值次数
        int allMemLowCount = 0;
        // 堆内存超出次数
        int heapMemLowCount = 0;
        // 非堆内存超出次数
        int noHeapLowCount = 0;
        for (LogMonitorJvmStatusEntity status : statuses) {
            // 总内存超出
            if (status.getUseMem() > logMonitorEntity.getJvmTotalMem() * outMaxPro) {
                allMemOutCount++;
            } else if (status.getUseMem() < (logMonitorEntity.getHeapInitMem() + logMonitorEntity.getNoHeapInitMem()) * lowMinPro) {
                // 总内存太多
                allMemLowCount++;
            }

            // 堆内存超出
            if (status.getHeapUseMem() > logMonitorEntity.getHeapTotalMem() * outMaxPro) {
                heapMemOutCount++;
            } else if (status.getHeapUseMem() < logMonitorEntity.getHeapInitMem() * lowMinPro) {
                // 堆内存太多
                heapMemLowCount++;
            }

            //非堆内存超出(注,非堆内存取得的值一般都是0或者-1 不能作逻辑处理,这里将初始化内存作为阈值)
            if (status.getNoHeapUseMem() > logMonitorEntity.getNoHeapInitMem() * outMaxPro) {
                noHeapOutCount++;
            } else if (status.getNoHeapUseMem() < logMonitorEntity.getNoHeapInitMem() * lowMinPro) {
                // 非堆内存太多
                noHeapLowCount++;
            }
        }

        // 总内存超了
        if (allMemOutCount > threshold) {
            list.add(ServiceQualityEnum.OUT_OF_JVM_MAX_MEMORY);
        }
        // 堆内存超了
        if (heapMemOutCount > threshold) {
            list.add(ServiceQualityEnum.OUT_OF_HEAP_MAX_MEMORY);
        }
        // 非堆内存超了
        if (noHeapOutCount > threshold) {
            list.add(ServiceQualityEnum.OUT_OF_NO_HEAP_MAX_MEMORY);
        }

        // 总内存太多
        if (allMemOutCount == 0 && allMemLowCount > threshold) {
            list.add(ServiceQualityEnum.NOT_ENOUGH_JVM_MAX_MEMORY);
        }
        // 堆内存太多
        if (heapMemOutCount == 0 && heapMemLowCount > threshold) {
            list.add(ServiceQualityEnum.NOT_ENOUGH_HEAP_MAX_MEMORY);
        }
        // 非堆内存太多
        if (noHeapOutCount == 0 && noHeapLowCount > threshold) {
            list.add(ServiceQualityEnum.NOT_ENOUGH_NO_HEAP_MAX_MEMORY);
        }


        return list;


    }
}
