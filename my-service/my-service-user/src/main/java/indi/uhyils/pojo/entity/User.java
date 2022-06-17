package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.context.UserContext;
import indi.uhyils.enums.Symbol;
import indi.uhyils.enums.UserStatusEnum;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.cqe.query.demo.Arg;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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

    /**
     * 强制登录用构造函数,不允许其他情况使用
     *
     * @param username
     * @param password
     * @param roleId
     */
    public User(UserName username, Password password, Identifier roleId) {
        super(parseUserNamePasswordToDO(username, password));
        this.toData().setRoleId(roleId.getId());
        /*token和缓存都需要使用id*/
        long id = String.format("%s&%s", data.getUsername(), data.getPassword()).hashCode();
        this.setUnique(new Identifier(id));
        toData().setId(id);
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
     * 加密密码
     */
    public void encodePassword() {
        Password password = new Password(data.getPassword());
        data.setPassword(password.encode());
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
        // 系统用户放行
        if (this.isSysRole()) {
            return true;
        }
        return rep.havePower(this, powerInfo);
    }

    /**
     * 判断此用户是不是系统用户
     *
     * @return
     */
    private boolean isSysRole() {
        return Objects.equals(toData().getRoleId(), UserContext.MYSQL_ROLE_ID);
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
        data.setPassword(password.encode());
        userRepository.save(this);

    }


    /**
     * 申请一个用户
     *
     * @return
     */
    public Identifier apply(UserRepository userRepository) {
        // 校验用户名
        Asserts.assertTrue(!userRepository.checkUserNameRepeat(this), "用户名重复");
        changeStatus(UserStatusEnum.APPLYING);
        return userRepository.save(this);
    }

    /**
     * 通过用户申请
     *
     * @param userRepository
     */
    public void passApply(UserRepository userRepository) {
        // 修改状态
        this.changeStatus(UserStatusEnum.USING);
        userRepository.change(this, Collections.singletonList(Arg.as(UserDO::getId, Symbol.EQ, this.unique.getId())));
    }

    /**
     * 停止一个用户
     *
     * @param userRepository
     */
    public void stopUser(UserRepository userRepository) {
        //修改状态
        this.changeStatus(UserStatusEnum.STOPED);
        userRepository.change(this, Collections.singletonList(Arg.as(UserDO::getId, Symbol.EQ, this.unique.getId())));
    }

    /**
     * 修改用户状态
     *
     * @param status
     */
    private void changeStatus(UserStatusEnum status) {
        toData().setStatus(status.getCode());
    }


    @Override
    public void perInsert() {
        // 常规填充
        super.perInsert();
        // 校验插入是否可以
        validationInsert();
        // 加密密码
        encodePassword();

    }

    /**
     * 插入前校验
     */
    private void validationInsert() {
        UserDO userDO = toData();
        String nickName = userDO.getNickName();
        Asserts.assertTrue(nickName != null, "昵称不能为空");
        Asserts.assertTrue(userDO.getPassword() != null, "密码不能为空");
        Asserts.assertTrue(userDO.getRoleId() != null, "用户角色不能为空");
        Asserts.assertTrue(userDO.getUsername() != null, "用户名不能为空");
        Asserts.assertTrue(userDO.getMail() != null, "邮箱不能为空");
        Asserts.assertTrue(userDO.getPhone() != null, "电话不能为空");
    }
}
