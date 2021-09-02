package indi.uhyils.service;


import indi.uhyils.pojo.DTO.ApiDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetByArgsAndGroupQuery;

/**
 * api表(Api)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分46秒
 */
public interface ApiService extends BaseDoService<ApiDTO> {


    /**
     * 获取所有的指定组下的api
     *
     * @param request 筛选信息
     *
     * @return 所有的指定组下的api
     */
    Page<ApiDTO> getByArgsAndGroup(GetByArgsAndGroupQuery request);

}
