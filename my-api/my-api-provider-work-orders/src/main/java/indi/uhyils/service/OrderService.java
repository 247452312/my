package indi.uhyils.service;

import indi.uhyils.pojo.request.*;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.DealOrderNodeResponse;
import indi.uhyils.pojo.response.InsertOrderResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.BaseService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工单
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时03分
 */
public interface OrderService extends BaseService {
    /**
     * 复制基础工单到工单
     *
     * @param request 工单本体
     * @return 插入后的id
     */
    ServiceResult<InsertOrderResponse> insertOrder(IdRequest request);

    /**
     * 提交工单
     *
     * @param request 提交工单请求
     * @return 工单
     */
    ServiceResult<Boolean> commitOrder(CommitOrderRequest request);

    /**
     * 撤回工单
     *
     * @param request
     * @return 是否发送信息到审批人成功
     */
    ServiceResult<Boolean> recallOrder(RecallOrderRequest request);

    /**
     * 同意撤回工单
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> agreeRecallOrder(AgreeRecallOrderRequest request);

    /**
     * 冻结工单(审批人才能操作)
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> frozenOrder(FrozenOrderRequest request);

    /**
     * 重启工单(对应冻结)
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> restartOrder(RestartOrderRequest request);

    /**
     * 工单节点失败(主动将工单节点置为失败)(处理人员经过核实,客观上不能完成此操作,例:审批时客户填写不合格)
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> failOrderNode(FailOrderNodeRequest request);


    /**
     * 处理工单节点
     *
     * @param request
     * @return
     */
    ServiceResult<DealOrderNodeResponse> dealOrderNode(DealOrderNodeRequest request) throws IOException, TimeoutException;

    /**
     * 工单节点(转交)失败(因处理人员无能力完成此节点,申请转交给其他人,则可以进行主动失败)
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> incapacityFailOrderNode(IncapacityFailOrderNodeRequest request);

    /**
     * 审批工单
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> approvalOrder(ApprovalOrderRequest request);

}
