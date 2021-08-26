package indi.uhyils.service.impl;

import indi.uhyils.assembler.InstructionsAssembler;
import indi.uhyils.repository.InstructionsRepository;
import indi.uhyils.pojo.DO.InstructionsDO;
import indi.uhyils.pojo.DTO.InstructionsDTO;
import indi.uhyils.pojo.entity.Instructions;
import indi.uhyils.service.InstructionsService;
import org.springframework.stereotype.Service;

/**
 * 说明书表(Instructions)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分43秒
 */
@Service
public class InstructionsServiceImpl extends AbstractDoService<InstructionsDO, Instructions, InstructionsDTO, InstructionsRepository, InstructionsAssembler> implements InstructionsService {

    public InstructionsServiceImpl(InstructionsAssembler assembler, InstructionsRepository repository) {
        super(assembler, repository);
    }


}
