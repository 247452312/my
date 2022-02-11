package indi.uhyils.facade;

import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.entity.type.Identifier;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月11日 20时12分
 */
public interface DynamicCodeFacade extends BaseFacade {

    /**
     * 根据groupId获取动态执行代码
     *
     * @param groupId
     *
     * @return
     */
    List<DynamicCodeDTO> getByGroupId(Identifier groupId);
}
