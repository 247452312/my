package indi.uhyils.service;

import indi.uhyils.pojo.request.*;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.InsertOrderResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.BaseService;

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
     * 修改临时节点(转交或者修改节点内容)
     *
     * @param request
     * @return 是否成功
     */
    ServiceResult<Boolean> updateTempOrder(UpdateTempOrderReqeust request);

    /**
     * 撤回工单
     *
     * @param request
     * @return 是否成功
     */
    ServiceResult<Boolean> recallOrder(RecallOrderReqeust request);

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
     * 工单节点失败
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
    ServiceResult<Boolean> dealOrderNode(DealOrderNodeRequest request);

    /**
     * 审批工单
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> approvalOrder(ApprovalOrderRequest request);

}
