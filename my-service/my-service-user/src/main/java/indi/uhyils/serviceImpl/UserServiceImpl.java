package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.content.Content;
import indi.uhyils.dao.UserDao;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.pojo.model.RoleEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.model.base.TokenInfo;
import indi.uhyils.pojo.request.LoginRequest;
import indi.uhyils.pojo.request.UpdatePasswordRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.LoginResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.service.UserService;
import indi.uhyils.util.AESUtil;
import indi.uhyils.util.MD5Util;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时51分
 */
@Service(group = "${spring.profiles.active}")
@ReadWriteMark(tables = {"sys_user"})
public class UserServiceImpl extends BaseDefaultServiceImpl<UserEntity> implements UserService {


    @Autowired
    private RedisPoolHandle redisPoolHandle;
    @Resource
    private UserDao dao;

    @Value("${token.salt}")
    private String salt;

    @Value("${token.encodeRules}")
    private String encodeRules;


    @Override
    @NoToken
    @ReadWriteMark(tables = {"sys_user", "sys_role", "sys_dept", "sys_role_dept", "sys_power", "sys_dept_power"})
    public ServiceResult<UserEntity> getUserById(IdRequest idRequest) {

        //缓存
        UserEntity user = redisPoolHandle.getUser(idRequest.getToken());
        if (user != null) {
            return ServiceResult.buildSuccessResult("查询成功", user, idRequest);
        }
        UserEntity userEntity = dao.getById(idRequest.getId());
        if (userEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null, idRequest);
        }
        initRole(userEntity);
        return ServiceResult.buildSuccessResult("查询成功", userEntity, idRequest);

    }

    private void initRole(UserEntity userEntity) {
        // 获取权限
        RoleEntity roleEntity = dao.getUserRoleById(userEntity.getRoleId());
        /* 注入dept*/
        List<DeptEntity> deptEntities = dao.getUserDeptsByRoleId(roleEntity.getId());
        roleEntity.setDepts(deptEntities);
        /* 注入power */
        for (DeptEntity deptEntity : deptEntities) {
            List<PowerEntity> powerEntities = dao.getUserPowerByDeptId(deptEntity.getId());
            deptEntity.setPowers(powerEntities);
        }
        userEntity.setRole(roleEntity);
    }


    @Override
    @NoToken
    @ReadWriteMark(cacheType = CacheTypeEnum.NOT_TYPE)
    public ServiceResult<String> getUserToken(IdRequest request) {
        String token = getToken(request.getId());
        return ServiceResult.buildSuccessResult("token生成成功", token, request);
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<UserEntity> insert) throws Exception {
        UserEntity data = insert.getData();
        data.preInsert(insert);
        data.setPassword(MD5Util.MD5Encode(data.getPassword()));
        return ServiceResult.buildSuccessResult("插入成功", dao.insert(data), insert);
    }


    @Override
    @NoToken
    public ServiceResult<TokenInfo> getTokenInfoByToken(DefaultRequest request) {
        String token = request.getToken();

        String tokenInfoString = AESUtil.AESDecode(encodeRules, token);

        assert tokenInfoString != null;
        String day = tokenInfoString.substring(0, 2);
        String hour = tokenInfoString.substring(2, 4);
        String mon = tokenInfoString.substring(4, 6);
        String sec = tokenInfoString.substring(6, 8);
        String random = tokenInfoString.substring(8, 10);


        long userId = Long.parseLong(tokenInfoString.substring(10, tokenInfoString.length() - 1 - salt.length()));

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setDay(Integer.parseInt(day));
        tokenInfo.setHour(Integer.parseInt(hour));
        tokenInfo.setMin(Integer.parseInt(mon));
        tokenInfo.setSec(Integer.parseInt(sec));
        tokenInfo.setRandom(Integer.parseInt(random));
        tokenInfo.setUserId(userId);
        Boolean aBoolean = redisPoolHandle.haveToken(token);
        if (aBoolean == null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddhhmm");
            String format = localDateTime.format(dateTimeFormatter);
            int dayNow = Integer.parseInt(format.substring(0, 2));
            int hourNow = Integer.parseInt(format.substring(2, 4));
            int monNow = Integer.parseInt(format.substring(4, 6));
            // 如果分钟差超过30
            if (monNow - Integer.parseInt(mon) >= Content.LOGIN_TIME_OUT_MIN) {
                tokenInfo.setTimeOut(true);
            } else if (hourNow - Integer.parseInt(hour) > 0) {
                tokenInfo.setTimeOut(true);
            } else if (dayNow - Integer.parseInt(day) > 0) {
                tokenInfo.setTimeOut(true);
            } else {
                tokenInfo.setTimeOut(false);
            }
        } else {
            tokenInfo.setTimeOut(!aBoolean);
        }
        return ServiceResult.buildSuccessResult("解密成功", tokenInfo, request);
    }

    @Override
    @NoToken
    public ServiceResult<LoginResponse> login(LoginRequest userRequest) {
        ArrayList<Arg> objects = new ArrayList<>();
        objects.add(new Arg("username", "=", userRequest.getUsername()));
        objects.add(new Arg("password", "=", MD5Util.MD5Encode(userRequest.getPassword())));
        ArrayList<UserEntity> byArgsNoPage = dao.getByArgsNoPage(objects);
        if (byArgsNoPage.size() != 1) {
            return ServiceResult.buildFailedResult("登录失败,用户名或密码不正确", LoginResponse.buildLoginFail(), userRequest);
        }
        UserEntity userEntity = byArgsNoPage.get(0);

        //检查是否已经登录,如果已经登录,则将之前已登录的挤下来
        Boolean haveUserId = redisPoolHandle.haveUserId(userEntity.getId());

        if (haveUserId != null && haveUserId) {
            redisPoolHandle.removeUserById(userEntity.getId());
        }

        String token = getToken(userEntity.getId());
        userRequest.setToken(token);

        // 登录->加入缓存中
        redisPoolHandle.addUser(token, userEntity);
        return ServiceResult.buildSuccessResult("成功", LoginResponse.buildLoginSuccess(token, userEntity), userRequest);
    }

    @Override
    public ServiceResult<Boolean> logout(DefaultRequest request) {
        Boolean result = redisPoolHandle.removeByKey(request.getToken());
        if (result) {
            result = redisPoolHandle.removeByKey(request.getUser().getId().toString());
        }
        return ServiceResult.buildSuccessResult("登出结束", result, request);
    }

    @Override
    public ServiceResult<ArrayList<UserEntity>> getUsers(DefaultRequest request) {
        ArrayList<UserEntity> all = dao.getAll();
        all.forEach(t -> {
            Long roleId = t.getRoleId();
            RoleEntity userRoleById = dao.getUserRoleById(roleId);
            t.setRole(userRoleById);
        });
        return ServiceResult.buildSuccessResult("查询成功", all, request);
    }

    @Override
    public ServiceResult<UserEntity> getUserByToken(DefaultRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", request.getUser(), request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<String> updatePassword(UpdatePasswordRequest request) {
        String oldPassword = request.getOldPassword();
        UserEntity user = request.getUser();
        Long userId = user.getId();
        Integer passwordIsTrue = dao.checkUserPassword(userId, MD5Util.MD5Encode(oldPassword));
        // 不为1 说明不正确
        if (passwordIsTrue != 1) {
            return ServiceResult.buildFailedResult("密码不正确", "密码不正确", request);
        }
        UserEntity byId = dao.getById(userId);
        byId.setPassword(request.getNewPassword());
        byId.preUpdate(request);
        dao.update(byId);
        return ServiceResult.buildSuccessResult("修改密码成功", "true", request);
    }

    @Override
    public ServiceResult<String> getNameById(IdRequest request) {
        String name = dao.getNameById(request.getId());
        return ServiceResult.buildSuccessResult("查询成功", name, request);
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEncodeRules() {
        return encodeRules;
    }

    public void setEncodeRules(String encodeRules) {
        this.encodeRules = encodeRules;
    }


    private String getToken(Long userId) {
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
        String str = userId.toString();
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

        return AESUtil.AESEncode(encodeRules, sb.toString());
    }


    @Override
    public UserDao getDao() {
        return dao;
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }


}
