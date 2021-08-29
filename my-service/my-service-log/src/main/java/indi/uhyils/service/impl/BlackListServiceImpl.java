package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.BlackListAssembler;
import indi.uhyils.builder.BlackListBuilder;
import indi.uhyils.dao.BlackListDao;
import indi.uhyils.dao.TraceDetailDao;
import indi.uhyils.pojo.DO.BlackListDO;
import indi.uhyils.pojo.DTO.BlackListDTO;
import indi.uhyils.pojo.DTO.request.AddBlackIpRequest;
import indi.uhyils.pojo.DTO.request.GetLogIntervalByIpRequest;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.entity.BlackList;
import indi.uhyils.repository.BlackListRepository;
import indi.uhyils.service.BlackListService;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 黑名单(BlackList)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分52秒
 */
@Service
@ReadWriteMark(tables = {"sys_black_list"})
public class BlackListServiceImpl extends AbstractDoService<BlackListDO, BlackList, BlackListDTO, BlackListRepository, BlackListAssembler> implements BlackListService {

    /**
     * 最大时间跨度 1秒
     */
    private static final Long MAX_TIME_SPAN = 1000L;

    /**
     * 很久很久以前 (3分钟)
     */
    private static final Long LONG_TIME_AGO = 3 * 60 * 1000L;

    @Resource
    private BlackListDao dao;

    @Resource
    private TraceDetailDao traceDetailDao;

    /**
     * 可以评价的最小 ip访问次数
     */
    @Value("${black-list.size}")
    private Integer canEvaluateMinSize;

    /**
     * 方差阈值 低于此阈值被认为是爬虫
     */
    @Value("${black-list.spider.frequency}")
    private Integer frequency;

    public BlackListServiceImpl(BlackListAssembler assembler, BlackListRepository repository) {
        super(assembler, repository);
    }

    @Override
    public Boolean getLogIntervalByIp(GetLogIntervalByIpRequest request) {
        String ip = request.getIp();
        //取指定ip最近的{canEvaluateMinSize}次访问时间节点
        List<Long> realTimes = traceDetailDao.getTimeByIp(ip, canEvaluateMinSize);
        // 如果次数不够,代表不能判断,返回不是爬虫的判断
        if (realTimes.size() < canEvaluateMinSize) {
            return false;
        }
        Long first = realTimes.get(0);
        Long end = realTimes.get(realTimes.size() - 1);
        // 时间跨度
        long timeSpan = first - end;
        // 如果时间已经过去很久了(指3分钟) 则返回不是爬虫
        if (System.currentTimeMillis() - first > LONG_TIME_AGO) {
            return false;
        }
        List<Long> timeIntervals = new ArrayList<>(realTimes.size() - 1);

        // 获取所有的时间间隔
        for (int i = 1; i < realTimes.size(); i++) {
            timeIntervals.add(realTimes.get(i) - realTimes.get(i - 1));
        }
        /* 求方差*/
        double result = getVariance(timeIntervals);
        // 求出来的方差小于阈值并且时间跨度小(每秒n个) 返回此ip可能是爬虫
        if (result <= frequency && timeSpan < MAX_TIME_SPAN) {
            LogUtil.info("ip:" + ip + " 最近12次请求的时间跨度为:" + timeSpan + " 方差为:" + result);
            return true;
        }
        return false;
    }

    private double getVariance(List<Long> times) {
        int sum = 0;
        double avg;
        for (Long time : times) {
            sum += time;
        }
        avg = (double) sum / times.size();
        double result = 0.0;

        for (Long time : times) {
            result += Math.pow(time - avg, 2);
        }
        result /= times.size();
        return result;
    }

    @Override
    public ArrayList<String> getAllIpBlackList(DefaultCQE request) {
        return dao.getAllIpBlackList();
    }

    @Override
    public Boolean addBlackIp(AddBlackIpRequest request) {
        BlackListDTO blackListEntity = BlackListBuilder.buildByIp(request.getIp());
        BlackList blackList = assem.toEntity(blackListEntity);
        rep.save(blackList);
        return true;
    }
}
