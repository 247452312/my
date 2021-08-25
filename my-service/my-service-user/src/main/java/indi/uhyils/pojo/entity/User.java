package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.Token;
import indi.uhyils.pojo.entity.type.UserId;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.util.MD5Util;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时29分
 */
public class User extends AbstractDoEntity<UserDO> {

    public User(UserDO userDO) {
        super(userDO);
    }

    /**
     * 批量注入角色
     *
     * @param userList
     */
    public static void batchInitRole(List<User> userList, RoleRepository roleRepository, DeptRepository deptRepository, PowerRepository powerRepository) {
        List<Long> roleIds = userList.stream().map(t -> t.toDo().getRoleId()).distinct().collect(Collectors.toList());
        List<Identifier> ids = roleIds.stream().map(Identifier::new).collect(Collectors.toList());
        List<Role> roles = roleRepository.find(ids);
        Map<Long, Role> idRoleMap = roles.stream().peek(t -> t.initDept(deptRepository, powerRepository)).collect(Collectors.toMap(t -> t.getId().getId(), t -> t));
        for (User user : userList) {
            Long roleId = user.toDo().getRoleId();
            Role role = idRoleMap.get(roleId);
            user.initRole(role);
        }

    }

    public boolean checkPassword(Password password) {
        return Objects.equals(password.getPassword(), data.getPassword());
    }

    public void encodePassword() {
        String password = data.getPassword();
        String encodePassword = MD5Util.MD5Encode(password);
        data.setPassword(encodePassword);
    }

    /**
     * 填充role
     *
     * @param roleRepository
     */
    public void initRole(RoleRepository roleRepository, DeptRepository deptRepository, PowerRepository powerRepository) {
        if (isEmpty()) {
            return;
        }
        Role role = roleRepository.find(new Identifier(getData().getRoleId()));
        role.initDept(deptRepository, powerRepository);
        RoleDO roleDO = role.toDo();
        getData().setRole(roleDO);
    }

    /**
     * 填充role
     *
     * @param role 角色
     */
    public void initRole(Role role) {
        if (isEmpty()) {
            return;
        }
        toDo().setRole(role.toDo());
    }

    /**
     * 用户转token
     *
     * @return
     */
    public Token parseToken(String salt, String encodeRules) {
        UserId userId = toId();
        return userId.toToken(salt, encodeRules);
    }

    public UserId toId() {
        return new UserId(getData().getId());
    }
}
