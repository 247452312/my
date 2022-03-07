package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.PowerInfo;
import indi.uhyils.pojo.entity.type.UserName;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.util.AESUtil;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.BeanUtil;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.MD5Util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
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
    private static final Long ADMIN_USER_ID = 0L;

    private Role role;

    private Token token;

    @Default
    public User(UserDO data) {
        super(data);
    }

    public User(Identifier userId) {
        super(userId, new UserDO());
    }

    public User(UserName userName, Password password) {
        super(parseUserNamePasswordToDO(userName, password));
    }

    public User(Long id) {
        super(id, new UserDO());
    }

    public User(UserDO userDO, RoleDO roleDO) {
        super(userDO);
        this.role = new Role(roleDO);
    }

    private static UserDO parseUserNamePasswordToDO(UserName userName, Password password) {
        UserDO userDO = new UserDO();
        userDO.setUsername(userName.getUserName());
        userDO.setPassword(password.getPassword());
        return userDO;
    }

    public static void batchInitRole(List<User> all, RoleRepository roleRepository, DeptRepository deptRepository, PowerRepository powerRepository, MenuRepository menuRepository) {
        List<Identifier> roleIds = all.stream().filter(t -> t.role == null).map(t -> t.toData().getRoleId()).distinct().map(Identifier::new).collect(Collectors.toList());
        List<Role> roles = roleRepository.find(roleIds);
        Map<Long, Role> idRoleMap = roles.stream().collect(Collectors.toMap(t -> t.toData().getId(), t -> t));
        for (User user : all) {
            if (user.haveRole()) {
                continue;
            }
            Long roleId = user.toData().getRoleId();
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
        Role role = roleRepository.find(new Identifier(data.getRoleId()));
        role.initDept(deptRepository, powerRepository, menuRepository);
        this.role = role;

    }

    /**
     * 填充role
     *
     * @param role 角色
     */
    public void forceInitRole(Role role) {
        this.role = role;
    }

    public void assertRole() {
        Asserts.assertTrue(role != null);
    }

    public List<Menu> screenMenu(List<Menu> menus) {
        // 管理员直接放行
        if (Objects.equals(ADMIN_USER_ID, toData().getId())) {
            return menus;
        }
        Asserts.assertTrue(role != null, "权限没有初始化");
        List<Menu> menu = role.menus();
        return menus.stream().filter(t -> CollectionUtil.contains(menu, t, entity -> entity.toData().getId())).collect(Collectors.toList());
    }


    public User login(UserRepository userRepository, String salt, String encodeRole) {
        Asserts.assertTrue(data.getUsername() != null);
        Asserts.assertTrue(data.getPassword() != null);

        /*查询是否正确*/
        User user = userRepository.checkLogin(this);
        BeanUtil.copyProperties(user.data, data);
        this.setUnique(user.unique);
        this.token = user.toToken(salt, encodeRole);
        return this;
    }

    public UserName username() {
        return new UserName(data.getUsername());
    }

    public Password password() {
        return new Password(data.getPassword());
    }


    public Token toToken(String salt, String encodeRules) {
        StringBuilder sb = new StringBuilder(26 + salt.length());

        //生成日期部分 8位
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddhhmmss");
        String format = localDateTime.format(dateTimeFormatter);
        sb.append(format);
        Random random = new Random(90);
        int randomNum = random.nextInt() + 10;
        //两位随机数 两位
        sb.append(randomNum);

        // 用户id 19位
        String str = getUnique().toString();
        long i = 19L - str.length();
        // long 最大19位 如果不够 最高位补0
        StringBuilder sbTemp = new StringBuilder(19);
        for (int j = 0; j < i; j++) {
            sbTemp.append("0");
        }
        sbTemp.append(str);
        sb.append(sbTemp);
        //盐 x位
        sb.append(salt);

        return new Token(getUnique().getId(), AESUtil.AESEncode(encodeRules, sb.toString()));
    }

    public Boolean havePower(PowerInfo powerInfo, PowerRepository rep) {
        return rep.havePower(this, powerInfo);
    }


    public void removeUserInRedis(UserRepository userRepository) {
        //检查是否已经登录,如果已经登录,则将之前已登录的挤下来
        Boolean haveUserId = userRepository.checkCacheUserId(this);

        if (haveUserId != null && haveUserId) {
            userRepository.removeUserInCacheById(this);
        }
    }

    public void addUserToRedis(UserRepository userRepository) {
        userRepository.cacheUser(token, this);
    }


    public String tokenValue() {
        return token.getToken();
    }

    public Boolean logout(UserRepository userRepository) {
        boolean result = userRepository.removeUserByToken(token);
        if (result) {
            result = userRepository.removeUserInCacheById(this);
        }
        return result;
    }

    public void checkPassword(Password password, UserRepository userRepository) {
        userRepository.checkPassword(this, password);
    }

    /**
     * 修改密码
     *
     * @param password
     * @param userRepository
     */
    public void changeToNewPassword(Password password, UserRepository userRepository) {
        data.setPassword(password.toMD5Str());
        userRepository.save(this);

    }

    /**
     * 强制登录
     *
     * @param salt        盐
     * @param encodeRules 加密信息
     * @param idUtil
     */
    public User forceLogin(String salt, String encodeRules, IdUtil idUtil) {
        Asserts.assertTrue(data.getUsername() != null);
        Asserts.assertTrue(data.getPassword() != null);

        /*token和缓存都需要使用id*/
        long id = idUtil.newId();
        this.setUnique(new Identifier(id));
        toData().setId(id);

        /*直接生成token*/
        this.token = toToken(salt, encodeRules);
        return this;
    }
}
