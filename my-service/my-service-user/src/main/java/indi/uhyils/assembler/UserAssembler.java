package indi.uhyils.assembler;

import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.User;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 08时55分
 */
@Assembler
public class UserAssembler extends AbstractAssembler<UserDO, User, UserDTO> {

    @Override
    public User toEntity(UserDO dO) {
        return new User(dO);
    }

    @Override
    public User toEntity(UserDTO dto) {
        return new User(toDo(dto));
    }

    @Override
    protected Class<UserDO> getDoClass() {
        return UserDO.class;
    }

    @Override
    protected Class<UserDTO> getDtoClass() {
        return UserDTO.class;
    }
}
