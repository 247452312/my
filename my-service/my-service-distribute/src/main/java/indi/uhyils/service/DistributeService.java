package indi.uhyils.service;

import indi.uhyils.pojo.DTO.response.welcome.WelcomeResponse;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月10日 08时24分
 */
public interface DistributeService extends BaseService {

    /**
     * 获取起始页信息
     *
     * @return
     */
    WelcomeResponse getWelcomeData();
}
