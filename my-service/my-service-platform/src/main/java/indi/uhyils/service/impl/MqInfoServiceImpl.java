package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.MqInfoAssembler;
import indi.uhyils.repository.MqInfoRepository;
import indi.uhyils.pojo.DO.MqInfoDO;
import indi.uhyils.pojo.DTO.MqInfoDTO;
import indi.uhyils.pojo.entity.MqInfo;
import indi.uhyils.service.MqInfoService;
import org.springframework.stereotype.Service;

/**
 * mq连接信息表(MqInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Service
@ReadWriteMark(tables = {"sys_mq_info"})
public class MqInfoServiceImpl extends AbstractDoService<MqInfoDO, MqInfo, MqInfoDTO, MqInfoRepository, MqInfoAssembler> implements MqInfoService {

    public MqInfoServiceImpl(MqInfoAssembler assembler, MqInfoRepository repository) {
        super(assembler, repository);
    }


}
