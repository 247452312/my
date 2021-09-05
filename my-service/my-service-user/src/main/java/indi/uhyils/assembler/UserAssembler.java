package indi.uhyils.assembler;

import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.entity.Role;
import indi.uhyils.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 08时55分
 */
@Assembler
public class UserAssembler extends AbstractAssembler<UserDO, User, UserDTO> {

    @Autowired
    private RoleAssembler roleAssembler;

    @Override
    public User toEntity(UserDO dO) {
        return new User(dO);
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User(toDo(dto));
        if (dto.getRole() != null) {
            Role role = roleAssembler.toEntity(dto.getRole());
            user.forceInitRole(role);
        }
        return user;
    }

    public User toEntity(DefaultCQE cqe) {
        UserDTO dto = cqe.getUser();
        return toEntity(dto);
    }

    @Override
    protected Class<UserDO> getDoClass() {
        return UserDO.class;
    }

    @Override
    protected Class<UserDTO> getDtoClass() {
        return UserDTO.class;
    }

    @Override
    public UserDTO toDTO(User entity) {
        UserDTO userDTO = super.toDTO(entity);
        Role role = entity.role();
        if (role != null) {
            RoleDTO roleDTO = roleAssembler.toDTO(entity.role());
            userDTO.setRole(roleDTO);
        }
        return userDTO;
    }
}
