package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.PushMsgDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.CronRequest;
import indi.uhyils.pojo.DTO.request.PushMsgToSomeoneRequest;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 推送日志表(PushMsg)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分05秒
 */
public interface PushMsgProvider extends DTOProvider<PushMsgDTO> {


    /**
     * 定时任务触发指定cron的推送
     *
     * @param request cron请求
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> push(CronRequest request) ;

    /**
     * 主动向某人推送一条消息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> pushMsgToSomeone(PushMsgToSomeoneRequest request) ;

}

