package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.HttpInfoAssembler;
import indi.uhyils.repository.HttpInfoRepository;
import indi.uhyils.pojo.DO.HttpInfoDO;
import indi.uhyils.pojo.DTO.HttpInfoDTO;
import indi.uhyils.pojo.entity.HttpInfo;
import indi.uhyils.service.HttpInfoService;
import org.springframework.stereotype.Service;

/**
 * http连接表(HttpInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Service
@ReadWriteMark(tables = {"sys_http_info"})
public class HttpInfoServiceImpl extends AbstractDoService<HttpInfoDO, HttpInfo, HttpInfoDTO, HttpInfoRepository, HttpInfoAssembler> implements HttpInfoService {

    public HttpInfoServiceImpl(HttpInfoAssembler assembler, HttpInfoRepository repository) {
        super(assembler, repository);
    }


}
