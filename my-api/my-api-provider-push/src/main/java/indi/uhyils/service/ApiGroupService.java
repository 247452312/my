package indi.uhyils.service;

import indi.uhyils.pojo.model.ApiGroupEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

import java.util.ArrayList;

/**
 * 外界api调用表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时22分
 */
public interface ApiGroupService extends DefaultEntityService<ApiGroupEntity> {

    /**
     * 测试api
     *
     * @param request api id
     * @return 结果
     */
    ServiceResult<String> test(IdRequest request);

    /**
     * 获取可以被订阅的所有api群
     *
     * @param request 默认请求
     * @return 可以被订阅的api群
     */
    ServiceResult<ArrayList<ApiGroupEntity>> getCanBeSubscribed(DefaultRequest request);


}
