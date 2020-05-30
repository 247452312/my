package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.UserDao;
import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.pojo.model.RoleEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.model.base.TokenInfo;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.GetUserRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.LoginRequest;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.LoginResponse;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.UserService;
import indi.uhyils.util.AESUtil;
import indi.uhyils.util.MD5Util;
import indi.uhyils.util.RedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserServiceImpl extends DefaultServiceImpl<UserEntity> implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RedisPoolUtil redisPoolUtil;
    @Autowired
    private UserDao dao;

    @Value("${token.salt}")
    private String salt;

    @Value("${token.encodeRules}")
    private String encodeRules;


    @Override
    public ServiceResult<UserEntity> getUserByIdNoToken(IdRequest idRequest) {

        //缓存
        UserEntity user = redisPoolUtil.getUser(idRequest.getToken());
        if (user != null) {
            return ServiceResult.buildSuccessResult("查询成功", user, idRequest);
        }
        List<UserEntity> byId = dao.getById(idRequest.getId());
        if (byId != null && byId.size() == 1) {
            UserEntity userEntity = byId.get(0);
            initRole(userEntity);
            return ServiceResult.buildSuccessResult("查询成功", userEntity, idRequest);
        }
        return ServiceResult.buildFailedResult("查无此人", null, idRequest);
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
    public ServiceResult<String> getUserTokenNoToken(GetUserRequest userRequest) {
        String token = getToken(userRequest.getId());
        return ServiceResult.buildSuccessResult("token生成成功", token, userRequest);
    }


    @Override
    public ServiceResult<TokenInfo> getTokenInfoByTokenNoToken(DefaultRequest request) {
        String token = request.getToken();

        String tokenInfo_ = AESUtil.AESDecode(encodeRules, token);

        String day = tokenInfo_.substring(0, 2);
        String hour = tokenInfo_.substring(2, 4);
        String mon = tokenInfo_.substring(4, 6);
        String sec = tokenInfo_.substring(6, 8);
        String random = tokenInfo_.substring(8, 10);


        String userId = tokenInfo_.substring(10, tokenInfo_.length() - 1 - salt.length());

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setDay(Integer.parseInt(day));
        tokenInfo.setHour(Integer.parseInt(hour));
        tokenInfo.setMin(Integer.parseInt(mon));
        tokenInfo.setSec(Integer.parseInt(sec));
        tokenInfo.setRandom(Integer.parseInt(random));
        tokenInfo.setUserId(userId);
        tokenInfo.setTimeOut(!redisPoolUtil.haveToken(token));
        return ServiceResult.buildSuccessResult("解密成功", tokenInfo, request);
    }

    @Override
    public ServiceResult<LoginResponse> userLoginNoToken(LoginRequest userRequest) {
        ArrayList<Arg> objects = new ArrayList<>();
        objects.add(new Arg("username", "=", userRequest.getUsername()));
        objects.add(new Arg("password", "=", MD5Util.MD5Encode(userRequest.getPassword())));
        ArrayList<UserEntity> byArgsNoPage = dao.getByArgsNoPage(objects);
        if (byArgsNoPage.size() != 1) {
            return ServiceResult.buildFailedResult("登录失败,用户名或密码不正确", LoginResponse.buildLoginFail(), userRequest);
        }
        UserEntity userEntity = byArgsNoPage.get(0);

        String token = getToken(userEntity.getId());
        userRequest.setToken(token);


        if (userEntity.getRoleId() == null) {
            return ServiceResult.buildSuccessResult("成功", LoginResponse.buildLoginSuccess(token, userEntity), userRequest);
        }
        initRole(userEntity);
        // 登录->加入缓存中 TODO 如果重复登录问题
        redisPoolUtil.addUser(token, userEntity);
        return ServiceResult.buildSuccessResult("成功", LoginResponse.buildLoginSuccess(token, userEntity), userRequest);
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
        StringBuilder sb = new StringBuilder(27 + salt.length());

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

        String token = AESUtil.AESEncode(encodeRules, sb.toString());

        return token;
    }


    public UserDao getDao() {
        return dao;
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }


}
