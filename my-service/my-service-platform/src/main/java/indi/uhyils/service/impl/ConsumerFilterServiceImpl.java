package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ConsumerFilterAssembler;
import indi.uhyils.repository.ConsumerFilterRepository;
import indi.uhyils.pojo.DO.ConsumerFilterDO;
import indi.uhyils.pojo.DTO.ConsumerFilterDTO;
import indi.uhyils.pojo.entity.ConsumerFilter;
import indi.uhyils.service.ConsumerFilterService;
import org.springframework.stereotype.Service;

/**
 * 消费过滤表(ConsumerFilter)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@Service
@ReadWriteMark(tables = {"sys_consumer_filter"})
public class ConsumerFilterServiceImpl extends AbstractDoService<ConsumerFilterDO, ConsumerFilter, ConsumerFilterDTO, ConsumerFilterRepository, ConsumerFilterAssembler> implements ConsumerFilterService {

    public ConsumerFilterServiceImpl(ConsumerFilterAssembler assembler, ConsumerFilterRepository repository) {
        super(assembler, repository);
    }


}
