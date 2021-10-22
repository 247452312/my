package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.InterfaceInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.AddInterfaceCommand;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 接口信息表(InterfaceInfo)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分40秒
 */
public interface InterfaceInfoProvider extends DTOProvider<InterfaceInfoDTO> {

    /**
     * 添加接口
     *
     * @param command
     *
     * @return
     */
    ServiceResult<InterfaceInfoDTO> addInterface(AddInterfaceCommand command);
}

