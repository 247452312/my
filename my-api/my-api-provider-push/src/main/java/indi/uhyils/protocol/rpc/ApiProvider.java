package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.ApiDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetByArgsAndGroupQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * api表(Api)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分47秒
 */
public interface ApiProvider extends DTOProvider<ApiDTO> {


    /**
     * 获取所有的指定组下的api
     *
     * @param request 筛选信息
     *
     * @return 所有的指定组下的api
     */
    Page<ApiDTO> getByArgsAndGroup(GetByArgsAndGroupQuery request);

}

