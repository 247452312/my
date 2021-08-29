package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.AgreeRecallOrderEvent;
import indi.uhyils.pojo.DTO.request.ApprovalOrderEvent;
import indi.uhyils.pojo.DTO.request.CommitOrderCommand;
import indi.uhyils.pojo.DTO.request.DealOrderNodeCommand;
import indi.uhyils.pojo.DTO.request.FailOrderNodeCommand;
import indi.uhyils.pojo.DTO.request.FrozenOrderCommand;
import indi.uhyils.pojo.DTO.request.IncapacityFailOrderNodeCommand;
import indi.uhyils.pojo.DTO.request.RecallOrderCommand;
import indi.uhyils.pojo.DTO.request.RestartOrderCommand;
import indi.uhyils.pojo.DTO.response.DealOrderNodeResponse;
import indi.uhyils.pojo.DTO.response.InitOrderResponse;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.protocol.rpc.base.BaseProvider;

/**
 * 工单
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时03分
 */
public interface OrderProvider extends BaseProvider {

    /**
     * 复制基础工单到工单
     *
     * @param request 工单本体
     *
     * @return 插入后的id
     */
    ServiceResult<InitOrderResponse> initOrder(IdCommand request) throws Exception;

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
     * 工单节点失败(主动将工单节点置为失败)(处理人员经过核实,客观上不能完成此操作,例:审批时客户填写不合格)
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> failOrderNode(FailOrderNodeCommand request);


    /**
     * 处理工单节点
     *
     * @param request
     *
     * @return
     */
    ServiceResult<DealOrderNodeResponse> dealOrderNode(DealOrderNodeCommand request) throws Exception;

    /**
     * 工单节点(转交)失败(因处理人员无能力完成此节点,申请转交给其他人,则可以进行主动失败)
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> incapacityFailOrderNode(IncapacityFailOrderNodeCommand request) throws Exception;

    /**
     * 审批工单
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> approvalOrder(ApprovalOrderEvent request) throws Exception;

}
