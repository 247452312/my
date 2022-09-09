package indi.uhyils.service;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.service.MysqlSdkService;
import indi.uhyils.pojo.cqe.InvokeCommand;

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


}
