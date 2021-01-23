package indi.uhyils.serviceImpl;

import indi.uhyils.dao.LogDao;
import indi.uhyils.dao.MonitorDao;
import indi.uhyils.dao.MonitorInterfaceDetailDao;
import indi.uhyils.dao.MonitorJvmStatusDetailDao;
import indi.uhyils.enum_.ServiceQualityEnum;
import indi.uhyils.pojo.model.MonitorDO;
import indi.uhyils.pojo.model.MonitorJvmStatusDetailDO;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.JvmDataStatisticsResponse;
import indi.uhyils.pojo.response.JvmInfoLogResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.JvmService;
import indi.uhyils.util.JvmStatusAnalysisUtil;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
public class JvmServiceImpl implements JvmService {

    @Resource
    private MonitorDao monitorDao;
    @Resource
    private MonitorInterfaceDetailDao monitorInterfaceDetailDao;
    @Resource
    private MonitorJvmStatusDetailDao monitorJvmStatusDetailDao;
    @Resource
    private LogDao logDao;


    @Override
    public ServiceResult<JvmDataStatisticsResponse> getJvmDataStatisticsResponse(DefaultRequest request) {
        /* 获取服务在线数量 */
        List<MonitorDO> monitorDOList = monitorDao.getOnlineService(System.currentTimeMillis());
        Integer onlineServiceCount = monitorDOList.size();
        /* 获取服务运行质量(以外关闭的系统,内存溢出风险的系统) */
        HashMap<Long, List<ServiceQualityEnum>> map = new HashMap<>(monitorDOList.size());
        for (MonitorDO monitorDO : monitorDOList) {
            List<MonitorJvmStatusDetailDO> list = monitorJvmStatusDetailDao.getByMonitorId(monitorDO.getId());
            List<ServiceQualityEnum> analysis = JvmStatusAnalysisUtil.analysis(monitorDO, list);
            map.put(monitorDO.getId(), analysis);
        }
        // 由于无法分辨前台发送请求是哪一个服务的 所以这里以现在正在运行的jvm最早启动时间为开始时间
        /* 获取前台请求次数 */
        Long firstStartTile = System.currentTimeMillis();
        for (MonitorDO monitorDO : monitorDOList) {
            if (monitorDO.getTime() < firstStartTile) {
                firstStartTile = monitorDO.getTime();

            }
        }
        Integer webRequestCount = logDao.getCountByStartTime(firstStartTile);
        /* 获取接口调用次数 */
        Integer interfaceCellCount = monitorInterfaceDetailDao.getCountByStartTime(firstStartTile);

        return ServiceResult.buildSuccessResult("查询数据统计JVM部分信息成功", JvmDataStatisticsResponse.build(onlineServiceCount, map, webRequestCount, interfaceCellCount), request);
    }

    @Override
    public ServiceResult<JvmInfoLogResponse> getJvmInfoLogResponse(DefaultRequest request) {
        /*1.获取所有的活着的服务的监控信息*/
        long now = System.currentTimeMillis();
        List<MonitorDO> onlineService = monitorDao.getOnlineService(now);
        HashMap<String, List> map = new HashMap<>(onlineService.size());
        for (MonitorDO monitorDO : onlineService) {
            /*2.获取每一个服务的详细信息 -> List*/
            List<MonitorJvmStatusDetailDO> monitorStatuses = monitorJvmStatusDetailDao.getByMonitorId(monitorDO.getId());
            assert monitorStatuses.size() > 0;
            /*获取开始时间*/

            MonitorJvmStatusDetailDO monitorJvmStatusDetailDO = monitorStatuses.get(0);
            Long startTime = monitorJvmStatusDetailDO.getTime();

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
            for (MonitorJvmStatusDetailDO monitorStatus : monitorStatuses) {
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
            map.put(monitorDO.getServiceName() + ":" + monitorDO.getIp(), list);
        }
        JvmInfoLogResponse build = JvmInfoLogResponse.build(map);
        return ServiceResult.buildSuccessResult("查询JVM内存信息成功", build, request);
    }
}
