package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ApiGroupAssembler;
import indi.uhyils.pojo.DO.ApiGroupDO;
import indi.uhyils.pojo.DTO.ApiGroupDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.ApiGroup;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ApiGroupRepository;
import indi.uhyils.repository.ApiRepository;
import indi.uhyils.service.ApiGroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * api组表(ApiGroup)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分51秒
 */
@Service
@ReadWriteMark(tables = {"sys_api_group"})
public class ApiGroupServiceImpl extends AbstractDoService<ApiGroupDO, ApiGroup, ApiGroupDTO, ApiGroupRepository, ApiGroupAssembler> implements ApiGroupService {

    @Autowired
    private ApiRepository apiRepository;

    public ApiGroupServiceImpl(ApiGroupAssembler assembler, ApiGroupRepository repository) {
        super(assembler, repository);
    }

    @Override
    public String test(IdQuery request) {
        ApiGroup apiGroup = new ApiGroup(request.getId());
        apiGroup.fillApi(apiRepository);
        return apiGroup.callApi();
    }

    @Override
    public List<ApiGroupDTO> getCanBeSubscribed(DefaultCQE request) {
        List<ApiGroup> apiGroups = rep.getCanBeSubscribed();
        return assem.listEntityToDTO(apiGroups);
    }

    @Override
    public Integer remove(Identifier id) {
        ApiGroup apiGroup = new ApiGroup(id.getId());
        apiGroup.removeSelf(rep);
        return apiGroup.removeApis(apiRepository);
    }
}
