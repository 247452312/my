package indi.uhyils.service;


import com.alibaba.fastjson.JSON;
import indi.uhyils.pojo.DTO.InterfaceInfoDTO;
import indi.uhyils.pojo.cqe.command.AddInterfaceCommand;
import indi.uhyils.pojo.cqe.command.InvokeInterfaceCommand;

/**
 * 接口信息表(InterfaceInfo)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
public interface InterfaceInfoService extends BaseDoService<InterfaceInfoDTO> {

    /**
     * 添加接口
     *
     * @param command
     *
     * @return
     */
    InterfaceInfoDTO addInterface(AddInterfaceCommand command);

    /**
     * 调用接口
     *
     * @param command
     *
     * @return 可能是JSONArray 也有可能是JSONObject
     *
     * @throws Exception
     */
    JSON invokeInterface(InvokeInterfaceCommand command) throws Exception;
}
