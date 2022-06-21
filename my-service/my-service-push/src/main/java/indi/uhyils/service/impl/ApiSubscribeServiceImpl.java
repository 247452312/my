package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ApiSubscribeAssembler;
import indi.uhyils.pojo.DO.ApiSubscribeDO;
import indi.uhyils.pojo.DTO.ApiSubscribeDTO;
import indi.uhyils.pojo.DTO.request.SubscribeRequest;
import indi.uhyils.pojo.entity.ApiSubscribe;
import indi.uhyils.repository.ApiSubscribeRepository;
import indi.uhyils.service.ApiSubscribeService;
import org.springframework.stereotype.Service;

/**
 * api订阅表(ApiSubscribe)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分55秒
 */
@Service
@ReadWriteMark(tables = {"sys_api_subscribe"})
public class ApiSubscribeServiceImpl extends AbstractDoService<ApiSubscribeDO, ApiSubscribe, ApiSubscribeDTO, ApiSubscribeRepository, ApiSubscribeAssembler> implements ApiSubscribeService {

    public ApiSubscribeServiceImpl(ApiSubscribeAssembler assembler, ApiSubscribeRepository repository) {
        super(assembler, repository);
    }


    @Override
    public Boolean subscribe(SubscribeRequest request) {
        ApiSubscribeDTO dto = assem.toDTO(request);
        ApiSubscribe apiSubscribe = assem.toEntity(dto);
        apiSubscribe.checkRepeat(rep);
        rep.save(apiSubscribe);
        return true;
    }
}
