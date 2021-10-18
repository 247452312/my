package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ConsumerInfoAssembler;
import indi.uhyils.repository.ConsumerInfoRepository;
import indi.uhyils.pojo.DO.ConsumerInfoDO;
import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.entity.ConsumerInfo;
import indi.uhyils.service.ConsumerInfoService;
import org.springframework.stereotype.Service;

/**
 * 服务消费方信息表(ConsumerInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@Service
@ReadWriteMark(tables = {"sys_consumer_info"})
public class ConsumerInfoServiceImpl extends AbstractDoService<ConsumerInfoDO, ConsumerInfo, ConsumerInfoDTO, ConsumerInfoRepository, ConsumerInfoAssembler> implements ConsumerInfoService {

    public ConsumerInfoServiceImpl(ConsumerInfoAssembler assembler, ConsumerInfoRepository repository) {
        super(assembler, repository);
    }


}
