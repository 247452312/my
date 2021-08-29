package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.LogMonitorAssembler;
import indi.uhyils.dao.LogMonitorDao;
import indi.uhyils.dao.MonitorJvmStatusDetailDao;
import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.enum_.ServiceQualityEnum;
import indi.uhyils.pojo.DO.LogMonitorDO;
import indi.uhyils.pojo.DO.LogMonitorJvmStatusDO;
import indi.uhyils.pojo.DTO.LogMonitorDTO;
import indi.uhyils.pojo.DTO.response.JvmDataStatisticsResponse;
import indi.uhyils.pojo.DTO.response.JvmInfoLogResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.entity.LogMonitor;
import indi.uhyils.repository.LogMonitorRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.JvmStatusAnalysisUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;


/**
 * JVM日志表(LogMonitor)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@Repository
public class LogMonitorRepositoryImpl extends AbstractRepository<LogMonitor, LogMonitorDO, LogMonitorDao, LogMonitorDTO, LogMonitorAssembler> implements LogMonitorRepository {

    @Resource
    private LogMonitorDao monitorDao;

    @Resource
    private TraceInfoDao traceInfoDao;

    @Resource
    private MonitorJvmStatusDetailDao monitorJvmStatusDetailDao;

    protected LogMonitorRepositoryImpl(LogMonitorAssembler convert, LogMonitorDao dao) {
        super(convert, dao);
    }

    @Override
    public JvmDataStatisticsResponse getJvmDataStatisticsResponse(DefaultCQE request) {
        /* 获取服务在线数量 */
        List<LogMonitorDO> logMonitorEntityList = monitorDao.getOnlineService(System.currentTimeMillis());
        Integer onlineServiceCount = logMonitorEntityList.size();
        /* 获取服务运行质量(以外关闭的系统,内存溢出风险的系统) */
        HashMap<Long, List<ServiceQualityEnum>> map = new HashMap<>(logMonitorEntityList.size());
        for (LogMonitorDO logMonitorEntity : logMonitorEntityList) {
            List<LogMonitorJvmStatusDO> list = monitorJvmStatusDetailDao.getByMonitorId(logMonitorEntity.getId());
            List<ServiceQualityEnum> analysis = JvmStatusAnalysisUtil.analysis(logMonitorEntity, list);
            map.put(logMonitorEntity.getId(), analysis);
        }
        // 由于无法分辨前台发送请求是哪一个服务的 所以这里以现在正在运行的jvm最早启动时间为开始时间
        Long firstStartTile = System.currentTimeMillis();
        for (LogMonitorDO logMonitorEntity : logMonitorEntityList) {
            if (logMonitorEntity.getTime() < firstStartTile) {
                firstStartTile = logMonitorEntity.getTime();
            }
        }
        /* 获取前台请求次数 */
        Integer webRequestCount = traceInfoDao.getCountByTypeAndStartTime(LogTypeEnum.CONTROLLER.getCode(), firstStartTile);
        /* 获取接口调用次数 */
        Integer interfaceCellCount = traceInfoDao.getCountByTypeAndStartTime(LogTypeEnum.RPC.getCode(), firstStartTile);

        return JvmDataStatisticsResponse.build(onlineServiceCount, map, webRequestCount, interfaceCellCount);
    }

    @Override
    public JvmInfoLogResponse getJvmInfoLogResponse(DefaultCQE request) {
        /*1.获取所有的活着的服务的监控信息*/
        long now = System.currentTimeMillis();
        List<LogMonitorDO> onlineService = monitorDao.getOnlineService(now);
        HashMap<String, List> map = new HashMap<>(onlineService.size());
        for (LogMonitorDO logMonitorEntity : onlineService) {
            /*2.获取每一个服务的详细信息 -> List*/
            List<LogMonitorJvmStatusDO> monitorStatuses = monitorJvmStatusDetailDao.getByMonitorId(logMonitorEntity.getId());
            assert monitorStatuses.size() > 0;
            /*获取开始时间*/

            LogMonitorJvmStatusDO logMonitorJvmStatusEntity = monitorStatuses.get(0);
            Long startTime = logMonitorJvmStatusEntity.getTime();

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
            for (LogMonitorJvmStatusDO monitorStatus : monitorStatuses) {
                Long time = monitorStatus.getTime();
                String format = simpleDateFormat.format(new Date(time));
                xAxix.add(format);
                BigDecimal noHeapUseMem = BigDecimal.valueOf(monitorStatus.getNoHeapUseMem());
                noHeapMem.add(noHeapUseMem.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                BigDecimal heapUseMem = BigDecimal.valueOf(monitorStatus.getHeapUseMem());
                heapMem.add(heapUseMem.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            List<List<?>> list = new ArrayList();
            list.add(xAxix);
            list.add(noHeapMem);
            list.add(heapMem);
            map.put(logMonitorEntity.getServiceName() + ":" + logMonitorEntity.getIp(), list);
        }
        return JvmInfoLogResponse.build(map);
    }

}
