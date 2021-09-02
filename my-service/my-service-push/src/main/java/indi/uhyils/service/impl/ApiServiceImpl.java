package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ApiAssembler;
import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.DTO.ApiDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetByArgsAndGroupQuery;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.entity.Api;
import indi.uhyils.repository.ApiRepository;
import indi.uhyils.service.ApiService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * api表(Api)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分47秒
 */
@Service
@ReadWriteMark(tables = {"sys_api"})
public class ApiServiceImpl extends AbstractDoService<ApiDO, Api, ApiDTO, ApiRepository, ApiAssembler> implements ApiService {

    public ApiServiceImpl(ApiAssembler assembler, ApiRepository repository) {
        super(assembler, repository);
    }


    @Override
    public Page<ApiDTO> getByArgsAndGroup(GetByArgsAndGroupQuery request) {
        List<Arg> args = request.getArgs();
        Arg e = new Arg();
        e.setName("api_group_id");
        e.setSymbol("=");
        e.setData(request.getGroupId());
        args.add(e);
        Page<Api> apiPage = rep.find(request);
        return assem.pageToDTO(apiPage);
    }
}
