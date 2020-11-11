package indi.uhyils.task;

import indi.uhyils.content.Content;
import indi.uhyils.dao.LogDao;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.DictItemEntity;
import indi.uhyils.pojo.request.GetByCodeRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.DictService;
import indi.uhyils.util.DefaultRequestBuildUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.NacosUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

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
    private volatile static Boolean degradation = false;
    @Resource
    private LogDao logDao;
    @Reference(group = "${spring.profiles.active}", check = false)
    private DictService dictService;

    @Scheduled(cron = "*/2 * * * * ?")
    public void demoSchedule() {
        // 获取每秒网关的并发数
        Integer countByStartTime = logDao.getCountByStartTime(System.currentTimeMillis() - 1000L);
        //获取字典中人工设置的自动降级的并发数
        GetByCodeRequest request = new GetByCodeRequest();
        DefaultRequest adminDefaultRequest = DefaultRequestBuildUtil.getAdminDefaultRequest();
        request.setUser(adminDefaultRequest.getUser());
        request.setRequestLink(adminDefaultRequest.getRequestLink());
        request.setCode(Content.CONCURRENT_NUM_DICT_CODE);
        ServiceResult<ArrayList<DictItemEntity>> byCode = dictService.getByCode(request);
        if (byCode == null || !byCode.getServiceCode().equals(ServiceCode.SUCCESS.getText()) || byCode.getData().size() == 0) {
            LogUtil.warn("获取服务字典中的并发数错误,请检查");
            return;
        }
        ArrayList<DictItemEntity> data = byCode.getData();
        DictItemEntity dictItemEntity = data.get(0);
        Long concurrentNumberSetable = Long.valueOf(dictItemEntity.getValue());


        //如果已经降级过了
        if (degradation) {
            // 如果设置的并发数的80%大于已有并发数,说明高峰已经过去(设置80% 防抖)
            if (concurrentNumberSetable * RECOVERY_PRE > countByStartTime) {
                NacosUtil.recoveryLowInterface();
                degradation = false;
            }
        } else {
            // 如果设置的并发数小于现有的并发数
            if (concurrentNumberSetable < countByStartTime) {
                NacosUtil.degradationLowInterface();
                degradation = true;
            }
        }

    }
}
