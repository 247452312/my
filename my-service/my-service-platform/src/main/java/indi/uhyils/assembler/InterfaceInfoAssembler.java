package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.DTO.InterfaceInfoDTO;
import indi.uhyils.pojo.cqe.command.AddInterfaceCommand;
import indi.uhyils.pojo.entity.ProviderInfo;
import indi.uhyils.pojo.entity.interfaces.InterfaceInfo;
import indi.uhyils.pojo.entity.interfaces.InterfaceInterface;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ProviderInfoRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接口信息表(InterfaceInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Mapper(componentModel = "spring")
public abstract class InterfaceInfoAssembler extends AbstractAssembler<InterfaceInfoDO, InterfaceInfo, InterfaceInfoDTO> {

    @Autowired
    private ProviderInfoRepository providerInfoRepository;

    /**
     * 转换为interface
     *
     * @param command
     *
     * @return
     */
    public InterfaceInfo toEntity(AddInterfaceCommand command) {
        InterfaceInfoDO interfaceInfo = toDo(command);
        ProviderInfo providerInfo = providerInfoRepository.findByUniqueKey(command.getProviderUniqueKey());
        Asserts.assertTrue(providerInfo != null, "生产者不存在");
        Identifier providerInfoId = providerInfo.getUnique();
        interfaceInfo.setProviderId(providerInfoId.getId());
        return toEntity(interfaceInfo);
    }

    /**
     * 转换为interface
     *
     * @param command
     *
     * @return
     */
    public abstract InterfaceInfoDO toDo(AddInterfaceCommand command);

    public List<InterfaceInterface> listDoToEntityInterface(List<InterfaceInfoDO> childsInterface) {
        if (CollectionUtil.isEmpty(childsInterface)) {
            return Collections.emptyList();
        }
        List<InterfaceInfo> interfaceInfos = listToEntity(childsInterface);
        return interfaceInfos.stream().map(t -> (InterfaceInterface) t).collect(Collectors.toList());
    }
}

