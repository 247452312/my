package indi.uhyils.protocol.task;

import indi.uhyils.context.MyContext;
import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetByCodeRequest;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.DictProvider;
import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.util.DefaultRequestBuildUtil;
import indi.uhyils.util.NacosUtil;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 并发数统计
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 06时28分
 */
@Component
public class ConcurrentNumberTask {


    private static final Double RECOVERY_PRE = 0.8;

    /**
     * 低级服务是否降级(默认没有降级)
     */
    private volatile Boolean degradation = Boolean.FALSE;

    @Resource
    private TraceInfoDao traceInfoDao;

    @RpcReference
    private DictProvider dictService;

    @Scheduled(cron = "*/2 * * * * ?")
    public void demoSchedule() {
        // 获取每秒网关的并发数
        Integer countByStartTime = traceInfoDao.getCountByTypeAndStartTime(LogTypeEnum.CONTROLLER.getCode(), System.currentTimeMillis() - 1000L);
        //获取字典中人工设置的自动降级的并发数
        GetByCodeRequest request = new GetByCodeRequest();
        DefaultCQE adminDefaultRequest = DefaultRequestBuildUtil.getAdminDefaultRequest();
        request.setUser(adminDefaultRequest.getUser());
        request.setCode(MyContext.CONCURRENT_NUM_DICT_CODE);
        ServiceResult<List<DictItemDTO>> code = dictService.getByCode(request);
        List<DictItemDTO> data = code.validationAndGet();
        DictItemDTO dictItemEntity = data.get(0);
        Long concurrentNumberSetable = Long.parseLong(dictItemEntity.getValue());

        //如果已经降级过了
        if (degradation) {
            // 如果设置的并发数的80%大于已有并发数,说明高峰已经过去(设置80% 防抖)
            if (concurrentNumberSetable * RECOVERY_PRE > countByStartTime) {
                NacosUtil.recoveryLowInterface();
                degradation = Boolean.FALSE;
            }
        } else {
            // 如果设置的并发数小于现有的并发数
            if (concurrentNumberSetable < countByStartTime) {
                NacosUtil.degradationLowInterface();
                degradation = Boolean.TRUE;
            }
        }

    }
}
