package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.InterfaceInfoAssembler;
import indi.uhyils.repository.InterfaceInfoRepository;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.DTO.InterfaceInfoDTO;
import indi.uhyils.pojo.entity.InterfaceInfo;
import indi.uhyils.service.InterfaceInfoService;
import org.springframework.stereotype.Service;

/**
 * 接口信息表(InterfaceInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Service
@ReadWriteMark(tables = {"sys_interface_info"})
public class InterfaceInfoServiceImpl extends AbstractDoService<InterfaceInfoDO, InterfaceInfo, InterfaceInfoDTO, InterfaceInfoRepository, InterfaceInfoAssembler> implements InterfaceInfoService {

    public InterfaceInfoServiceImpl(InterfaceInfoAssembler assembler, InterfaceInfoRepository repository) {
        super(assembler, repository);
    }


}
