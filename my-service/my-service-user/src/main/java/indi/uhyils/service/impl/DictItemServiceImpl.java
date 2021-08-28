package indi.uhyils.service.impl;

import indi.uhyils.assembler.DictItemAssembler;
import indi.uhyils.pojo.DO.DictItemDO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.entity.DictItem;
import indi.uhyils.repository.DictItemRepository;
import indi.uhyils.service.DictItemService;
import org.springframework.stereotype.Service;

/**
 * 数据字典子项(DictItem)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分43秒
 */
@Service
public class DictItemServiceImpl extends AbstractDoService<DictItemDO, DictItem, DictItemDTO, DictItemRepository, DictItemAssembler> implements DictItemService {

    public DictItemServiceImpl(DictItemAssembler assembler, DictItemRepository repository) {
        super(assembler, repository);
    }


}
