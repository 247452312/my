package indi.uhyils.service;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.service.MysqlSdkService;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.pojo.dto.response.GetInterfaceInfoResponse;

/**
 * sdk gateway对外提供的方法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月15日 09时52分
 */
public interface GatewaySdkService extends BaseService, MysqlSdkService {

    /**
     * 执行远程请求
     *
     * @param command
     *
     * @return
     */
    NodeInvokeResult invokeInterface(InvokeCommand command);

    /**
     * 执行节点
     *
     * @param command
     *
     * @return
     */
    NodeInvokeResult invokeNode(InvokeCommand command);

    /**
     * 获取接口信息
     *
     * @param command 同 invoke 方便调用
     *
     * @return
     */
    GetInterfaceInfoResponse getInterfaceInfo(InvokeCommand command);


}
