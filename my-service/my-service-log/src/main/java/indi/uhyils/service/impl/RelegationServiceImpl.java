package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.RelegationAssembler;
import indi.uhyils.facade.ServiceControlFacade;
import indi.uhyils.pojo.DO.RelegationDO;
import indi.uhyils.pojo.DTO.RelegationDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.event.CheckAndAddRelegationEvent;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import indi.uhyils.pojo.entity.Relegation;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.RelegationRepository;
import indi.uhyils.service.RelegationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 接口降级策略(Relegation)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分24秒
 */
@Service
@ReadWriteMark(tables = {"sys_relegation"})
public class RelegationServiceImpl extends AbstractDoService<RelegationDO, Relegation, RelegationDTO, RelegationRepository, RelegationAssembler> implements RelegationService {

    @Autowired
    private ServiceControlFacade facade;


    public RelegationServiceImpl(RelegationAssembler assembler, RelegationRepository repository) {
        super(assembler, repository);
    }

    @Override
    public void checkAndAddRelegation(CheckAndAddRelegationEvent event) {
        Relegation relegation = new Relegation(
            event.getTraceDetailDTO().getType(),
            event.getTraceDetailDTO().getOtherOne(),
            event.getTraceDetailDTO().getOtherTwo());

        relegation.validate();

        boolean repeat = relegation.checkRepeat(rep);
        if (repeat) {
            return;
        }
        // 设置默认值
        relegation.initDefault();
        // 保存
        relegation.saveSelf(rep);

    }

    @Override
    public Page<RelegationDTO> query(List<Arg> args, Order order, Limit limit) {
        Page<RelegationDTO> query = super.query(args, order, limit);
        List<RelegationDTO> list = query.getList();
        facade.fillDisable(list);
        return query;
    }

    @Override
    public List<RelegationDTO> query(List<Identifier> ids) {
        List<RelegationDTO> query = super.query(ids);
        facade.fillDisable(query);
        return query;
    }

    @Override
    public List<RelegationDTO> queryNoPage(List<Arg> args, Order order) {
        List<RelegationDTO> dtos = super.queryNoPage(args, order);
        facade.fillDisable(dtos);
        return dtos;
    }

    @Override
    public RelegationDTO query(Identifier id) {
        RelegationDTO query = super.query(id);
        facade.fillDisable(query);
        return query;
    }

    @Override
    public Boolean demotion(String serviceName, String methodName) {
        Relegation relegation = new Relegation(serviceName, methodName);
        return relegation.demotion(facade);
    }

    @Override
    public Boolean recover(String serviceName, String methodName) {
        Relegation relegation = new Relegation(serviceName, methodName);
        return relegation.recover(facade);
    }
}
