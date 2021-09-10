package indi.uhyils.pojo.entity;

import indi.uhyils.enum_.ServiceQualityEnum;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.pojo.DO.LogMonitorDO;
import indi.uhyils.pojo.DO.LogMonitorJvmStatusDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.LogMonitorJvmStatusRepository;
import indi.uhyils.repository.LogMonitorRepository;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.CollectionUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JVM日志表(LogMonitor)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
public class LogMonitor extends AbstractDoEntity<LogMonitorDO> {


    /**
     * 可分析的最小值
     */
    private static final Integer STATUS_SIZE_MIN = 6;

    /**
     * 此服务的历史状态记录
     */
    private List<LogMonitorJvmStatus> statuses;

    /**
     * 此服务的唯一标示
     */
    private JvmUniqueMark unique;

    /**
     * 服务分析
     */
    private List<ServiceQualityEnum> qualitys;

    public LogMonitor(LogMonitorDO dO) {
        super(dO);
    }

    public LogMonitor(JvmUniqueMark unique, LogMonitorDO logMonitorDO, List<LogMonitorJvmStatusDO> logMonitorJvmStatusDOS) {
        super(logMonitorDO);
        this.unique = unique;
        this.statuses = logMonitorJvmStatusDOS.stream().map(LogMonitorJvmStatus::new).collect(Collectors.toList());

    }


    public String echartKey() {
        return toDo().getServiceName() + ":" + toDo().getIp();
    }

    /**
     * 分析jvm运行信息
     *
     * @return 是否健康
     */
    public List<ServiceQualityEnum> analysis() {
        List<ServiceQualityEnum> list = new ArrayList<>();
        if (statuses.size() < STATUS_SIZE_MIN) {
            // 如果状态信息没有超过6个 也就是系统没有运行超过3个小时 没有分析的必要 直接返回正常
            list.add(ServiceQualityEnum.GOOD);
            return list;
        }
        /* 1.如果程序总内存除了刚刚启动以外一直在增加,判断为内存溢出,警告 */
        boolean memStillUp = true;
        for (int i = 1; i < statuses.size(); i++) {
            if (statuses.get(i).toDo().getUseMem() <= statuses.get(i - 1).toDo().getUseMem()) {
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
        for (LogMonitorJvmStatus statuses : statuses) {
            // 总内存超出
            LogMonitorJvmStatusDO status = statuses.toDo();
            LogMonitorDO monitor = toDo();
            if (status.getUseMem() > monitor.getJvmTotalMem() * outMaxPro) {
                allMemOutCount++;
            } else if (status.getUseMem() < (monitor.getHeapInitMem() + monitor.getNoHeapInitMem()) * lowMinPro) {
                // 总内存太多
                allMemLowCount++;
            }
            // 堆内存超出
            if (status.getHeapUseMem() > monitor.getHeapTotalMem() * outMaxPro) {
                heapMemOutCount++;
            } else if (status.getHeapUseMem() < monitor.getHeapInitMem() * lowMinPro) {
                // 堆内存太多
                heapMemLowCount++;
            }
            //非堆内存超出(注,非堆内存取得的值一般都是0或者-1 不能作逻辑处理,这里将初始化内存作为阈值)
            if (status.getNoHeapUseMem() > monitor.getNoHeapInitMem() * outMaxPro) {
                noHeapOutCount++;
            } else if (status.getNoHeapUseMem() < monitor.getNoHeapInitMem() * lowMinPro) {
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

    public List<ServiceQualityEnum> qualitys() {
        AssertUtil.assertTrue(qualitys != null, "未分析结果");
        return qualitys;
    }

    public Long id() {
        return toDo().getId();
    }

    public void initStatuses(LogMonitorJvmStatusRepository repository) {
        if (statuses != null) {
            return;
        }
        this.statuses = repository.listLogMonitorJvmStatus(this);
    }

    public void initServiceQuality(LogMonitorJvmStatusRepository repository) {
        if (this.qualitys != null) {
            return;
        }
        if (statuses == null) {
            initStatuses(repository);
        }
        this.qualitys = analysis();
    }

    public Long startTime() {
        return toDo().getTime();
    }

    public List makeEchart(long now) {
        LogMonitorJvmStatus logMonitorJvmStatusEntity = statuses.get(0);
        Long startTime = logMonitorJvmStatusEntity.toDo().getTime();

        SimpleDateFormat simpleDateFormat;
        if (now - startTime > (long) 24 * 60 * 60 * 1000) {
            // 如果多于1天
            simpleDateFormat = new SimpleDateFormat("dd日 hh:mm");
        } else {
            simpleDateFormat = new SimpleDateFormat("hh:mm");
        }
        /*4.制作x轴*/
        List<String> xAxix = new ArrayList<>();
        List<Double> noHeapMem = new ArrayList<>();
        List<Double> heapMem = new ArrayList<>();
        for (LogMonitorJvmStatus monitorStatus : statuses) {
            LogMonitorJvmStatusDO logMonitorJvmStatusDO = monitorStatus.toDo();
            Long time = logMonitorJvmStatusDO.getTime();
            String format = simpleDateFormat.format(new Date(time));
            xAxix.add(format);
            BigDecimal noHeapUseMem = BigDecimal.valueOf(logMonitorJvmStatusDO.getNoHeapUseMem());
            noHeapMem.add(noHeapUseMem.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            BigDecimal heapUseMem = BigDecimal.valueOf(logMonitorJvmStatusDO.getHeapUseMem());
            heapMem.add(heapUseMem.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        List<List<?>> list = new ArrayList();
        list.add(xAxix);
        list.add(noHeapMem);
        list.add(heapMem);
        return list;
    }

    public void checkMonitorRepeat(LogMonitorRepository rep) {
        Integer i = rep.checkMonitorRepeat(unique);
        AssertUtil.assertTrue(i == 0, "此服务已存在,不能重复启动");
    }

    /**
     * 查询有没有同样ip 且同样服务名称的,如果有,将endtime设置为现在,表示发现停止的时间
     *
     * @param rep
     */
    public void changeMonitorThatRepeatByIpAndName(LogMonitorRepository rep) {
        rep.changeMonitorThatRepeatByIpAndName(data.getServiceName(), unique.getIp(), System.currentTimeMillis());

    }

    public void addSelf(LogMonitorRepository rep) {
        Identifier save = rep.save(this);
        for (LogMonitorJvmStatus status : statuses) {
            status.data.setFid(save.getId());
        }
    }

    public void addStatus(LogMonitorJvmStatusRepository repository) {
        repository.save(statuses);
    }

    public void changeEndTimeLag(LogMonitorRepository rep) {
        long endTime = 0L;
        LogMonitorJvmStatus lastEndTimeStatus = null;
        if (CollectionUtil.isEmpty(statuses)) {
            return;
        }
        for (LogMonitorJvmStatus status : statuses) {
            Long time = status.data.getTime();
            if (time > endTime) {
                endTime = time;
                lastEndTimeStatus = status;
            }
        }
        // 修改结束时间为假想时间
        AssertUtil.assertTrue(lastEndTimeStatus.data != null && lastEndTimeStatus.data.getFid() != null, "假想时间所在状态没有fid");
        lastEndTimeStatus.changeEndTimeLag(rep);

    }
}
