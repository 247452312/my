package indi.uhyils.service;


import indi.uhyils.pojo.DTO.DealOrderNodeDTO;
import indi.uhyils.pojo.DTO.OrderNodeDTO;
import indi.uhyils.pojo.cqe.command.DealOrderNodeCommand;
import indi.uhyils.pojo.cqe.command.FailOrderNodeCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.command.IncapacityFailOrderNodeCommand;

/**
 * 工单节点样例表(OrderNode)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分21秒
 */
public interface OrderNodeService extends BaseDoService<OrderNodeDTO> {

    /**
     * 批量删除,删除工单时用
     *
     * @param request
     *
     * @return
     */
    Boolean deleteByIds(IdsCommand request);

    /**
     * 工单节点失败(主动将工单节点置为失败)(处理人员经过核实,客观上不能完成此操作,例:审批时客户填写不合格)
     *
     * @param request
     *
     * @return
     */
    Boolean failOrderNode(FailOrderNodeCommand request);

    /**
     * 处理工单节点
     *
     * @param request
     *
     * @return
     */
    DealOrderNodeDTO dealOrderNode(DealOrderNodeCommand request);

    /**
     * 工单节点(转交)失败(因处理人员无能力完成此节点,申请转交给其他人,则可以进行主动失败)
     *
     * @param request
     *
     * @return
     */
    Boolean incapacityFailOrderNode(IncapacityFailOrderNodeCommand request);

}
