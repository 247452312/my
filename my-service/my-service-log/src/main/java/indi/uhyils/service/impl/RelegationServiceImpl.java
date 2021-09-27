package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.RelegationAssembler;
import indi.uhyils.pojo.DO.RelegationDO;
import indi.uhyils.pojo.DTO.RelegationDTO;
import indi.uhyils.pojo.cqe.event.CheckAndAddRelegationEvent;
import indi.uhyils.pojo.entity.Relegation;
import indi.uhyils.repository.RelegationRepository;
import indi.uhyils.service.RelegationService;
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
}
