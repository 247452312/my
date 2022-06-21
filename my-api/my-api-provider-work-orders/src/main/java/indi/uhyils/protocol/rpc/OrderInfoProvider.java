package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.InitOrderDTO;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.CommitOrderCommand;
import indi.uhyils.pojo.cqe.command.FrozenOrderCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.RecallOrderCommand;
import indi.uhyils.pojo.cqe.command.RestartOrderCommand;
import indi.uhyils.pojo.cqe.event.AgreeRecallOrderEvent;
import indi.uhyils.pojo.cqe.event.ApprovalOrderEvent;
import indi.uhyils.pojo.cqe.query.GetAllOrderQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;

/**
 * 工单基础信息样例表(OrderInfo)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分14秒
 */
public interface OrderInfoProvider extends DTOProvider<OrderInfoDTO> {


    /**
     * 复制基础工单到工单
     *
     * @param request 工单本体
     *
     * @return 插入后的id
     */
    ServiceResult<InitOrderDTO> initOrder(IdCommand request);

    /**
     * 根据类型获取全部工单
     *
     * @param request
     *
     * @return
     */
    ServiceResult<List<OrderInfoDTO>> getAllOrder(GetAllOrderQuery request);


    /**
     * 提交工单
     *
     * @param request 提交工单请求
     *
     * @return 工单
     */
    ServiceResult<Boolean> commitOrder(CommitOrderCommand request);

    /**
     * 撤回工单
     *
     * @param request
     *
     * @return 是否发送信息到审批人成功
     */
    ServiceResult<Boolean> recallOrder(RecallOrderCommand request);


    /**
     * 同意撤回工单
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> agreeRecallOrder(AgreeRecallOrderEvent request);


    /**
     * 冻结工单(审批人才能操作)
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> frozenOrder(FrozenOrderCommand request);

    /**
     * 重启工单(对应冻结)
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> restartOrder(RestartOrderCommand request);


    /**
     * 审批工单
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> approvalOrder(ApprovalOrderEvent request);
}

