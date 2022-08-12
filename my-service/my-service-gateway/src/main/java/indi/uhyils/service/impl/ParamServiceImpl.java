package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ParamAssembler;
import indi.uhyils.pojo.DO.ParamDO;
import indi.uhyils.pojo.DTO.ParamDTO;
import indi.uhyils.pojo.entity.Param;
import indi.uhyils.repository.ParamRepository;
import indi.uhyils.service.ParamService;
import org.springframework.stereotype.Service;

/**
 * 系统参数表(Param)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年06月17日 15时53分
 */
@Service
@ReadWriteMark(tables = {"sys_param"})
public class ParamServiceImpl extends AbstractDoService<ParamDO, Param, ParamDTO, ParamRepository, ParamAssembler> implements ParamService {

    public ParamServiceImpl(ParamAssembler assembler, ParamRepository repository) {
        super(assembler, repository);
    }


}
