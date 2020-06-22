package indi.uhyils.service;

import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.pojo.response.welcome.WelcomeResponse;

/**
 * 请求分发接口, 用于接收使用多个微服务进行数据组装的接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface DistributeService {

    /**
     * 获取首页数据
     *
     * @param request 默认请求
     * @return 数据
     */
    ServiceResult<WelcomeResponse> getWelcomeData(DefaultRequest request) throws Exception;

}
