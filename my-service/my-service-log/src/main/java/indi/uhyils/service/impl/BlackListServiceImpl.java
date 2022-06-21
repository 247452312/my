package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.BlackListAssembler;
import indi.uhyils.builder.BlackListBuilder;
import indi.uhyils.pojo.DO.BlackListDO;
import indi.uhyils.pojo.DTO.BlackListDTO;
import indi.uhyils.pojo.DTO.request.AddBlackIpRequest;
import indi.uhyils.pojo.DTO.request.GetLogIntervalByIpQuery;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.entity.BlackList;
import indi.uhyils.pojo.entity.UserSpiderBehavior;
import indi.uhyils.repository.BlackListRepository;
import indi.uhyils.repository.TraceDetailRepository;
import indi.uhyils.service.BlackListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 可以评价的最小 ip访问次数
     */
    @Value("${black-list.size}")
    private Integer canEvaluateMinSize;

    /**
     * 方差阈值 低于此阈值被认为是爬虫
     */
    @Value("${black-list.spider.frequency}")
    private Integer frequency;

    @Autowired
    private TraceDetailRepository traceDetailRepository;

    public BlackListServiceImpl(BlackListAssembler assembler, BlackListRepository repository) {
        super(assembler, repository);
    }

    @Override
    public Boolean getLogIntervalByIp(GetLogIntervalByIpQuery request) {
        UserSpiderBehavior userBehavior = new UserSpiderBehavior(request.getIp());
        userBehavior.fillLastTime(canEvaluateMinSize, traceDetailRepository);
        return userBehavior.judgeSpider(frequency);

    }

    @Override
    public List<String> getAllIpBlackList(DefaultCQE request) {
        return rep.findAllIpBlackList();
    }

    @Override
    public Boolean addBlackIp(AddBlackIpRequest request) {
        BlackListDTO blackListEntity = BlackListBuilder.buildByIp(request.getIp());
        BlackList blackList = assem.toEntity(blackListEntity);
        rep.save(blackList);
        return true;
    }
}
