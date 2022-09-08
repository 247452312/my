package indi.uhyils.service;


import indi.uhyils.pojo.DTO.CallNodeDTO;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import java.util.List;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public interface CallNodeService extends BaseDoService<CallNodeDTO> {


    /**
     * 查询外层节点
     *
     * @param args
     *
     * @return
     */
    List<CallNodeDTO> queryWithAllNode(List<Arg> args);
}
