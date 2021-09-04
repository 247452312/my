package indi.uhyils.facade;

import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.type.Identifier;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月04日 08时51分
 */
public interface UserFacade extends BaseFacade {


    /**
     * 根据用户id获取用户
     *
     * @param userId
     *
     * @return
     */
    UserDTO getById(Identifier userId);

    /**
     * 批量根据id获取用户
     *
     * @param userIds
     *
     * @return
     */
    List<UserDTO> getByIds(List<Long> userIds);

}
