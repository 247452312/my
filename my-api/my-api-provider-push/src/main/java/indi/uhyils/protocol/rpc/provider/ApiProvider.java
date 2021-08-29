package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.DTO.request.GetByArgsAndGroupRequest;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 外界api调用表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时22分
 */
public interface ApiProvider extends DTOProvider<ApiDO> {

    /**
     * 获取所有的指定组下的api
     *
     * @param request 筛选信息
     *
     * @return 所有的指定组下的api
     */
    ServiceResult<Page<ApiDO>> getByArgsAndGroup(GetByArgsAndGroupRequest request);
}
