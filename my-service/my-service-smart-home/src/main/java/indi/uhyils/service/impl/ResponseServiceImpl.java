package indi.uhyils.service.impl;

import indi.uhyils.assembler.ResponseAssembler;
import indi.uhyils.repository.ResponseRepository;
import indi.uhyils.pojo.DO.ResponseDO;
import indi.uhyils.pojo.DTO.ResponseDTO;
import indi.uhyils.pojo.entity.Response;
import indi.uhyils.service.ResponseService;
import org.springframework.stereotype.Service;

/**
 * 设备指令回应表(Response)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分25秒
 */
@Service
public class ResponseServiceImpl extends AbstractDoService<ResponseDO, Response, ResponseDTO, ResponseRepository, ResponseAssembler> implements ResponseService {

    public ResponseServiceImpl(ResponseAssembler assembler, ResponseRepository repository) {
        super(assembler, repository);
    }


}
