package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OutApiAssembler;
import indi.uhyils.pojo.DO.OutApiDO;
import indi.uhyils.pojo.DTO.OutApiDTO;
import indi.uhyils.pojo.entity.OutApi;
import indi.uhyils.repository.OutApiRepository;
import indi.uhyils.service.OutApiService;
import org.springframework.stereotype.Service;

/**
 * 开放api(OutApi)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分11秒
 */
@Service
@ReadWriteMark(tables = {"sys_out_api"})
public class OutApiServiceImpl extends AbstractDoService<OutApiDO, OutApi, OutApiDTO, OutApiRepository, OutApiAssembler> implements OutApiService {

    public OutApiServiceImpl(OutApiAssembler assembler, OutApiRepository repository) {
        super(assembler, repository);
    }


}
