package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.dao.UserDao;
import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.pojo.model.RoleEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.model.base.TokenInfo;
import indi.uhyils.pojo.request.*;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.LoginResponse;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.UserService;
import indi.uhyils.util.AESUtil;
import indi.uhyils.util.MD5Util;
import indi.uhyils.util.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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
public class UserServiceImpl extends BaseDefaultServiceImpl<UserEntity> implements UserService {


    @Autowired
    private RedisPoolUtil redisPoolUtil;
    @Autowired
    private UserDao dao;

    @Value("${token.salt}")
    private String salt;

    @Value("${token.encodeRules}")
    private String encodeRules;


    @Override
    @NoToken
    public ServiceResult<UserEntity> getUserByIdNoToken(IdRequest idRequest) {

        //缓存
        UserEntity user = redisPoolUtil.getUser(idRequest.getToken());
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
    public ServiceResult<String> getUserTokenNoToken(GetUserRequest userRequest) {
        String token = getToken(userRequest.getId());
        return ServiceResult.buildSuccessResult("token生成成功", token, userRequest);
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<UserEntity> insert) {
        UserEntity data = insert.getData();
        data.preInsert(insert);
        data.setPassword(MD5Util.MD5Encode(data.getPassword()));
        return ServiceResult.buildSuccessResult("插入成功", dao.insert(data), insert);
    }


    @Override
    @NoToken
    public ServiceResult<TokenInfo> getTokenInfoByTokenNoToken(DefaultRequest request) {
        String token = request.getToken();

        String tokenInfoString = AESUtil.AESDecode(encodeRules, token);

        assert tokenInfoString != null;
        String day = tokenInfoString.substring(0, 2);
        String hour = tokenInfoString.substring(2, 4);
        String mon = tokenInfoString.substring(4, 6);
        String sec = tokenInfoString.substring(6, 8);
        String random = tokenInfoString.substring(8, 10);


        String userId = tokenInfoString.substring(10, tokenInfoString.length() - 1 - salt.length());

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setDay(Integer.parseInt(day));
        tokenInfo.setHour(Integer.parseInt(hour));
        tokenInfo.setMin(Integer.parseInt(mon));
        tokenInfo.setSec(Integer.parseInt(sec));
        tokenInfo.setRandom(Integer.parseInt(random));
        tokenInfo.setUserId(userId);
        Boolean aBoolean = redisPoolUtil.haveToken(token);
        if (aBoolean == null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddhhmm");
            String format = localDateTime.format(dateTimeFormatter);
            Integer dayNow = Integer.parseInt(format.substring(0, 2));
            Integer hourNow = Integer.parseInt(format.substring(2, 4));
            Integer monNow = Integer.parseInt(format.substring(4, 6));
            // 如果分钟差超过30
            if (monNow - Integer.parseInt(mon) >= 30) {
                tokenInfo.setTimeOut(true);
            } else if (hourNow - Integer.parseInt(hour) >= 1) {
                tokenInfo.setTimeOut(true);
            } else if (dayNow - Integer.parseInt(day) >= 1) {
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
    public ServiceResult<LoginResponse> userLoginNoToken(LoginRequest userRequest) {
        ArrayList<Arg> objects = new ArrayList<>();
        objects.add(new Arg("username", "=", userRequest.getUsername()));
        objects.add(new Arg("password", "=", MD5Util.MD5Encode(userRequest.getPassword())));
        ArrayList<UserEntity> byArgsNoPage = dao.getByArgsNoPage(objects);
        if (byArgsNoPage.size() != 1) {
            return ServiceResult.buildFailedResult("登录失败,用户名或密码不正确", LoginResponse.buildLoginFail(), userRequest);
        }
        UserEntity userEntity = byArgsNoPage.get(0);

        //检查是否已经登录,如果已经登录,则将之前已登录的挤下来
        Boolean haveUserId = redisPoolUtil.haveUserId(userEntity.getId());

        if (haveUserId != null && haveUserId) {
            redisPoolUtil.removeUserById(userEntity.getId());
        }

        String token = getToken(userEntity.getId());
        userRequest.setToken(token);


        if (userEntity.getRoleId() == null) {
            return ServiceResult.buildSuccessResult("成功", LoginResponse.buildLoginSuccess(token, userEntity), userRequest);
        }
        // 登录->加入缓存中
        redisPoolUtil.addUser(token, userEntity);
        return ServiceResult.buildSuccessResult("成功", LoginResponse.buildLoginSuccess(token, userEntity), userRequest);
    }

    @Override
    public ServiceResult<ArrayList<UserEntity>> getUsers(DefaultRequest request) {
        ArrayList<UserEntity> all = dao.getAll();
        all.forEach(t -> {
            String roleId = t.getRoleId();
            RoleEntity userRoleById = dao.getUserRoleById(roleId);
            t.setRole(userRoleById);
        });
        return ServiceResult.buildSuccessResult("查询成功", all, request);
    }

    @Override
    public ServiceResult<UserEntity> getUserById(IdRequest request) {
        String id = request.getId();
        UserEntity userEntity = dao.getById(id);
        if (userEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null, request);
        }
        String roleId = userEntity.getRoleId();
        RoleEntity userRoleById = dao.getUserRoleById(roleId);
        userEntity.setRole(userRoleById);
        return ServiceResult.buildSuccessResult("获取用户成功", userEntity, request);
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


    private String getToken(String userId) {
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

        // 用户id 16位
        sb.append(userId);
        //盐 x位
        sb.append(salt);

        return AESUtil.AESEncode(encodeRules, sb.toString());
    }


    public UserDao getDao() {
        return dao;
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }


}
