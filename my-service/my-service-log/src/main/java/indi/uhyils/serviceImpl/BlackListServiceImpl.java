package indi.uhyils.serviceImpl;

import indi.uhyils.dao.BlackListDao;
import indi.uhyils.pojo.model.BlackListEntity;
import indi.uhyils.pojo.request.AddBlackIpRequest;
import indi.uhyils.pojo.request.GetLogIntervalByIpRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.BlackListService;
import indi.uhyils.util.LogUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;


/**
 * (BlackList)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月24日 06时40分49秒
 */
@Service(group = "${spring.profiles.active}")
public class BlackListServiceImpl extends BaseDefaultServiceImpl<BlackListEntity> implements BlackListService {
    /**
     * 最大时间跨度 1秒
     */
    private static final Long MAX_TIME_SPAN = 1000L;
    /**
     * 很久很久以前 (3分钟)
     */
    private static final Long LONG_TIME_AGO = 3 * 60 * 1000L;
    @Autowired
    private BlackListDao dao;
    /**
     * 可以评价的最小 ip访问次数
     */
    @Value("${black-list.size}")
    private Integer size;
    /**
     * 方差阈值 低于此阈值被认为是爬虫
     */
    @Value("${black-list.spider.frequency}")
    private Integer frequency;

    public BlackListDao getDao() {
        return this.dao;
    }

    public void setDao(BlackListDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<Boolean> getLogIntervalByIp(GetLogIntervalByIpRequest request) {
        String ip = request.getIp();
        List<Long> realTimes = dao.getTimeByIp(ip, size);

        if (realTimes.size() < size) {
            return ServiceResult.buildSuccessResult(null, false, request);
        }
        Long first = realTimes.get(0);
        Long end = realTimes.get(realTimes.size() - 1);
        // 时间跨度
        long timeSpan = first - end;
        // 如果时间已经过去很久了(指3分钟)
        if (System.currentTimeMillis() - first > LONG_TIME_AGO) {
            return ServiceResult.buildSuccessResult(false, request);
        }
        List<Long> times = new ArrayList<>(realTimes.size() - 1);

        for (int i = 1; i < realTimes.size(); i++) {
            times.add(realTimes.get(i) - realTimes.get(i - 1));
        }
        /* 求方差*/
        double result = getVariance(times);
        // 求出来的方差小于阈值并且时间跨度小(每秒n个) 返回此ip可能是爬虫
        if (result <= frequency && timeSpan < MAX_TIME_SPAN) {
            LogUtil.info("ip:" + ip + " 最近12次请求的时间跨度为:" + timeSpan + " 方差为:" + result);
            return ServiceResult.buildSuccessResult(true, request);
        }
        return ServiceResult.buildSuccessResult(false, request);
    }

    private double getVariance(List<Long> times) {
        int sum = 0;
        double avg;
        for (Long time : times) {
            sum += time;
        }
        avg = (double) sum / times.size();
        double result = 0.0;

        for (int i = 0; i < times.size(); i++) {
            result += Math.pow(times.get(i) - avg, 2);
        }
        result /= times.size();
        return result;
    }

    @Override
    public ServiceResult<ArrayList<String>> getAllIpBlackList(DefaultRequest request) {
        return ServiceResult.buildSuccessResult(dao.getAllIpBlackList(), request);
    }

    @Override
    public ServiceResult<Boolean> addBlackIp(AddBlackIpRequest request) {
        // TODO 记得把这里补上
        return null;
    }
}
