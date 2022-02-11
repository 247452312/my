package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.DynamicCodeHistoryAssembler;
import indi.uhyils.pojo.DO.DynamicCodeHistoryDO;
import indi.uhyils.pojo.DTO.DynamicCodeHistoryDTO;
import indi.uhyils.pojo.entity.DynamicCodeHistory;
import indi.uhyils.repository.DynamicCodeHistoryRepository;
import indi.uhyils.service.DynamicCodeHistoryService;
import org.springframework.stereotype.Service;

/**
* 动态代码历史记录表(DynamicCodeHistory)表 内部服务实现类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
@Service
@ReadWriteMark(tables = {"sys_dynamic_code_history"})
public class DynamicCodeHistoryServiceImpl extends AbstractDoService<DynamicCodeHistoryDO, DynamicCodeHistory, DynamicCodeHistoryDTO, DynamicCodeHistoryRepository, DynamicCodeHistoryAssembler> implements DynamicCodeHistoryService {

    public DynamicCodeHistoryServiceImpl(DynamicCodeHistoryAssembler assembler, DynamicCodeHistoryRepository repository) {
        super(assembler, repository);
    }


}
