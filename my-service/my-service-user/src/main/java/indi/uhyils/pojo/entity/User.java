package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.CollectionUtil;
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

    /**
     * admin的id
     */
    private static final Long ADMIN_USER_ID = 1L;

    private Role role;

    public User(UserDO userDO) {
        super(userDO);
    }

    public User(UserDO userDO, RoleDO roleDO) {
        super(userDO);
        this.role = new Role(roleDO);
    }

    public static void batchInitRole(List<User> all, RoleRepository roleRepository, DeptRepository deptRepository, PowerRepository powerRepository, MenuRepository menuRepository) {
        List<Identifier> roleIds = all.stream().filter(t -> t.role == null).map(t -> t.toDo().getRoleId()).distinct().map(Identifier::new).collect(Collectors.toList());
        List<Role> roles = roleRepository.find(roleIds);
        Map<Long, Role> idRoleMap = roles.stream().collect(Collectors.toMap(t -> t.toDo().getId(), t -> t));
        for (User user : all) {
            if (user.haveRole()) {
                continue;
            }
            Long roleId = user.toDo().getRoleId();
            Role role = idRoleMap.get(roleId);
            if (role == null) {
                continue;
            }
            role.initDept(deptRepository, powerRepository, menuRepository);
            user.forceInitRole(role);
        }
    }

    public boolean haveRole() {
        return role != null;
    }

    /**
     * 权限
     *
     * @return
     */
    public Role role() {
        return role;
    }

    /**
     * 检查密码是否正确
     *
     * @param password
     *
     * @return
     */
    public boolean checkPassword(Password password) {
        return Objects.equals(password.getPassword(), data.getPassword());
    }

    /**
     * 加密密码
     */
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
    public void initRole(RoleRepository roleRepository, DeptRepository deptRepository, PowerRepository powerRepository, MenuRepository menuRepository) {
        if (role != null) {
            return;
        }
        Role role = roleRepository.find(new Identifier(getData().getRoleId()));
        role.initDept(deptRepository, powerRepository, menuRepository);
        RoleDO roleDO = role.toDo();
        this.role = new Role(roleDO);

    }

    /**
     * 填充role
     *
     * @param role 角色
     */
    public void forceInitRole(Role role) {
        this.role = role;
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

    public void assertRole() {
        AssertUtil.assertTrue(role != null);
    }

    public List<Menu> screenMenu(List<Menu> menus) {
        // 管理员直接放行
        if (Objects.equals(ADMIN_USER_ID, toDo().getId())) {
            return menus;
        }
        AssertUtil.assertTrue(role != null, "权限没有初始化");
        List<Menu> menu = role.menus();
        return menus.stream().filter(t -> CollectionUtil.contains(menu, t, entity -> entity.toDo().getId())).collect(Collectors.toList());
    }
}
