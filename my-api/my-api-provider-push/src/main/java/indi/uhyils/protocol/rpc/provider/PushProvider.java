package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DTO.request.CronRequest;
import indi.uhyils.pojo.DTO.request.PushMsgToSomeoneRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.BaseProvider;

/**
 * 推送方法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月29日 06时49分
 */
public interface PushProvider extends BaseProvider {

    /**
     * 定时任务触发指定cron的推送
     *
     * @param request cron请求
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> push(CronRequest request) throws Exception;

    /**
     * 主动向某人推送一条消息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> pushMsgToSomeone(PushMsgToSomeoneRequest request) throws Exception;
}
