package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.SpaceAssembler;
import indi.uhyils.repository.SpaceRepository;
import indi.uhyils.pojo.DO.SpaceDO;
import indi.uhyils.pojo.DTO.SpaceDTO;
import indi.uhyils.pojo.entity.Space;
import indi.uhyils.service.SpaceService;
import org.springframework.stereotype.Service;

/**
 * 空间坐标表(Space)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分36秒
 */
@Service
@ReadWriteMark(tables = {"sys_space"})
public class SpaceServiceImpl extends AbstractDoService<SpaceDO, Space, SpaceDTO, SpaceRepository, SpaceAssembler> implements SpaceService {

    public SpaceServiceImpl(SpaceAssembler assembler, SpaceRepository repository) {
        super(assembler, repository);
    }


}
