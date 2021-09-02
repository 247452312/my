package indi.uhyils.service;


import indi.uhyils.pojo.DTO.ServerDTO;
import indi.uhyils.pojo.DTO.request.TestConnByDataRequest;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import java.util.List;

/**
 * 服务器表(Server)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分17秒
 */
public interface ServerService extends BaseDoService<ServerDTO> {

    /**
     * 测试服务器链接
     *
     * @param request
     *
     * @return
     */
    Boolean testConnByData(TestConnByDataRequest request);

    /**
     * 测试服务器链接
     *
     * @param request
     *
     * @return
     */
    Boolean testConnById(IdCommand request);

    /**
     * 获取所有服务器信息
     *
     * @param request 请求
     *
     * @return 所有服务器信息
     */
    List<ServerDTO> getServersIdAndName(DefaultCQE request);


    /**
     * 根据服务器的id获取服务器的用户名
     *
     * @param request 服务器id
     *
     * @return 服务器名称
     */
    String getNameById(IdQuery request);

}
