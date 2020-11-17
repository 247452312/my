package indi.uhyils.service;

import indi.uhyils.pojo.request.CronRequest;
import indi.uhyils.pojo.request.PushMsgToSomeoneRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.BaseService;

/**
 * 推送方法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月29日 06时49分
 */
public interface PushService extends BaseService {

    /**
     * 定时任务触发指定cron的推送
     *
     * @param request cron请求
     * @return 是否成功
     */
    ServiceResult<Boolean> push(CronRequest request);

    /**
     * 主动向某人推送一条消息
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> pushMsgToSomeone(PushMsgToSomeoneRequest request);
}
