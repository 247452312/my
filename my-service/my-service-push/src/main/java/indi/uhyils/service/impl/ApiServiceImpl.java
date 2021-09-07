package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ApiAssembler;
import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.DTO.ApiDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import indi.uhyils.pojo.entity.Api;
import indi.uhyils.repository.ApiRepository;
import indi.uhyils.service.ApiService;
import java.util.ArrayList;
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
    public Page<ApiDTO> getByArgsAndGroup(Long groupId, Order order, Limit limit) {
        List<Arg> args = new ArrayList<>();
        Arg e = new Arg();
        e.setName("api_group_id");
        e.setSymbol("=");
        e.setData(groupId);
        args.add(e);
        Page<Api> apiPage = rep.find(args, order, limit);
        return assem.pageToDTO(apiPage);
    }
}
