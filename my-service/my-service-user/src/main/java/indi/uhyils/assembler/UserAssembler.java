package indi.uhyils.assembler;

import indi.uhyils.enums.UserTypeEnum;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.entity.Role;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.Visiter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 08时55分
 */
@Mapper(componentModel = "spring")
public abstract class UserAssembler extends AbstractAssembler<UserDO, User, UserDTO> {

    @Autowired
    private RoleAssembler roleAssembler;

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
    public UserDTO toDTO(User entity) {
        if (entity instanceof Visiter) {
            return visiterToDTO((Visiter) entity);
        }
        UserDTO userDTO = toDTO(entity.toData());
        Role role = entity.role();
        userDTO.setUserType(UserTypeEnum.USER.getCode());
        if (role != null) {
            RoleDTO roleDTO = roleAssembler.toDTO(entity.role());
            userDTO.setRole(roleDTO);
        }
        return userDTO;
    }

    private UserDTO visiterToDTO(Visiter visiter) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserType(UserTypeEnum.VISITER.getCode());

        userDTO.setNickName("游客");
        userDTO.setUsername(null);
        userDTO.setPassword(null);
        userDTO.setMail(null);
        userDTO.setPhone(null);
        userDTO.setHeadPortrait(null);
        userDTO.setRoleId(null);
        userDTO.setRole(null);
        // 使用中
        userDTO.setStatus(1);
        userDTO.setToken(visiter.tokenValue());
        return userDTO;

    }
}
