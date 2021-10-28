package indi.uhyils.service.impl;

import com.alibaba.fastjson.JSON;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.InterfaceInfoAssembler;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.DTO.InterfaceInfoDTO;
import indi.uhyils.pojo.cqe.command.AddInterfaceCommand;
import indi.uhyils.pojo.cqe.command.InvokeInterfaceCommand;
import indi.uhyils.pojo.entity.interfaces.InterfaceInfo;
import indi.uhyils.pojo.entity.interfaces.InterfaceInterface;
import indi.uhyils.repository.ConsumerFilterRepository;
import indi.uhyils.repository.DbInfoRepository;
import indi.uhyils.repository.HttpInfoRepository;
import indi.uhyils.repository.InterfaceInfoRepository;
import indi.uhyils.repository.MqInfoRepository;
import indi.uhyils.service.InterfaceInfoService;
import indi.uhyils.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HttpInfoRepository httpInfoRepository;

    @Autowired
    private MqInfoRepository mqInfoRepository;

    @Autowired
    private DbInfoRepository dbInfoRepository;

    @Autowired
    private ConsumerFilterRepository consumerFilterRepository;

    public InterfaceInfoServiceImpl(InterfaceInfoAssembler assembler, InterfaceInfoRepository repository) {
        super(assembler, repository);
    }

    @Override
    public InterfaceInfoDTO addInterface(AddInterfaceCommand command) {
        InterfaceInfo interfaceInfo = assem.toEntity(command);
        interfaceInfo.saveSelf(rep);
        return assem.toDTO(interfaceInfo);
    }

    @Override
    public JSON invokeInterface(InvokeInterfaceCommand command) throws Exception {
        Long interfaceId = command.getInterfaceId();
        Asserts.assertTrue(interfaceId != null);
        InterfaceInfo interfaceInfo = new InterfaceInfo(interfaceId);
        // 填充本体
        interfaceInfo.completion(rep);
        // 递归填充子类
        interfaceInfo.completionChild(rep);
        // 转换为自身正确的类型, 例如HTTP接口转换为 HttpInterfaceInfoInterface
        interfaceInfo.findSourceAndFull(httpInfoRepository, mqInfoRepository, dbInfoRepository);
        InterfaceInterface interfaceInterface = interfaceInfo.transChildToMarkType();
        return interfaceInterface.invoke(command.getConsumerId(), command.getParam(), consumerFilterRepository);
    }

}
