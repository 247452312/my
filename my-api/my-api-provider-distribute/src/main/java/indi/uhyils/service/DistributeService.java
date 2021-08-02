package indi.uhyils.service;

import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.response.welcome.WelcomeResponse;
import indi.uhyils.service.base.BaseService;

/**
 * <p>这个服务属于一个微服务的中台,负责同一个方法同时调用多个微服务时进行请求分发
 * 并且进行数据组装与简单处理{@ps 尽量不要进行复杂的操作}
 * </p>
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface DistributeService extends BaseService {

    /**
     * 获取首页数据
     *
     * @param request 默认请求
     *
     * @return 数据
     *
     * @throws Exception 远程调用错误
     */
    ServiceResult<WelcomeResponse> getWelcomeData(DefaultRequest request) throws Exception;

}
