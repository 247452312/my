package indi.uhyils.service;

import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.pojo.request.CheckUserHavePowerRequest;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.response.ServiceResult;

import java.util.ArrayList;

/**
 * 权限接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface PowerService extends DefaultEntityService<PowerEntity> {

    /**
     * 获取所有的权限
     *
     * @param request 请求
     * @return 所有权限
     */
    ServiceResult<ArrayList<PowerEntity>> getPowers(DefaultRequest request);

    /**
     * 检查用户是否存在此权限
     *
     * @param request 检查用户是否存在此权限请求
     * @return 是否存在
     */
    ServiceResult<Boolean> checkUserHavePower(CheckUserHavePowerRequest request);
}
