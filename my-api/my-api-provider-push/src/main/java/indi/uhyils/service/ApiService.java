package indi.uhyils.service;

import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

/**
 * 外界api调用表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时22分
 */
public interface ApiService extends DefaultEntityService<ApiEntity> {

    /**
     * 测试api
     *
     * @param request api id
     * @return 结果
     */
    ServiceResult<String> test(IdRequest request);
}
