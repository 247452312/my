package indi.uhyils.facade;

import indi.uhyils.pojo.DTO.DictItemDTO;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 10时29分
 */
public interface DictFacade extends BaseFacade {


    /**
     * 根据code获取字典项
     *
     * @param code
     *
     * @return
     */
    List<DictItemDTO> getByCode(String code);
}
