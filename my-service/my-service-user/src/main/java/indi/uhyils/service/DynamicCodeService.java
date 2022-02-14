package indi.uhyils.service;


import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.DTO.request.GetByGroupIdQuery;
import java.util.List;

/**
 * 动态代码主表(DynamicCode)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年02月11日 18时53分
 */
public interface DynamicCodeService extends BaseDoService<DynamicCodeDTO> {

    /**
     * 根据groupId获取动态代码
     *
     * @param query
     *
     * @return
     */
    List<DynamicCodeDTO> getByGroupId(GetByGroupIdQuery query);
}
