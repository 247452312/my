package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.DynamicCodeAssembler;
import indi.uhyils.pojo.DO.DynamicCodeDO;
import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.DTO.request.GetByGroupIdQuery;
import indi.uhyils.pojo.entity.DynamicCode;
import indi.uhyils.pojo.entity.DynamicCodeGroup;
import indi.uhyils.repository.DynamicCodeRepository;
import indi.uhyils.service.DynamicCodeService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 动态代码主表(DynamicCode)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年02月11日 18时53分
 */
@Service
@ReadWriteMark(tables = {"sys_dynamic_code"})
public class DynamicCodeServiceImpl extends AbstractDoService<DynamicCodeDO, DynamicCode, DynamicCodeDTO, DynamicCodeRepository, DynamicCodeAssembler> implements DynamicCodeService {

    public DynamicCodeServiceImpl(DynamicCodeAssembler assembler, DynamicCodeRepository repository) {
        super(assembler, repository);
    }


    @Override
    public List<DynamicCodeDTO> getByGroupId(GetByGroupIdQuery query) {
        DynamicCodeGroup dynamicCode = new DynamicCodeGroup(query.getGroupId());
        dynamicCode.fillByGroup(rep);
        return dynamicCode.codes(assem);
    }
}
