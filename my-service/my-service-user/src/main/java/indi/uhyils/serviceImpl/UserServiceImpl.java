package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.UserDao;
import indi.uhyils.enum_.UserTypeEnum;
import indi.uhyils.exception.EnumParseNoHaveException;
import indi.uhyils.model.TokenInfo;
import indi.uhyils.model.UserRightEntity;
import indi.uhyils.request.*;
import indi.uhyils.model.UserEntity;
import indi.uhyils.request.model.Arg;
import indi.uhyils.response.LoginResponse;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.UserService;


import indi.uhyils.util.AESUtil;
import indi.uhyils.util.MD5Util;
import indi.uhyils.util.RedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时51分
 */
@Service
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RedisPoolUtil redisPoolUtil;
    @Autowired
    private UserDao userDao;

    @Value("${token.salt}")
    private String salt;

    @Value("${token.encodeRules}")
    private String encodeRules;

    @Override
    public ServiceResult<Page<UserEntity>> getByArgs(ArgsRequest argsRequest) {
        List<Arg> args = argsRequest.getArgs();
        Boolean paging = argsRequest.getPaging();
        if (paging == true) {
            ArrayList<UserEntity> byArgs = userDao.getByArgs(args, argsRequest.getPage(), argsRequest.getSize());
            int count = userDao.count();
            Page<UserEntity> build = Page.build(argsRequest, byArgs, count, (count / argsRequest.getSize()) + 1);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        } else {
            ArrayList<UserEntity> byArgs = userDao.getByArgsNoPage(args);
            int count = userDao.count();
            Page<UserEntity> build = Page.build(argsRequest, byArgs, count, null);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        }
    }

    @Override
    public ServiceResult<UserEntity> getById(IdRequest idRequest) {
        List<UserEntity> byId = userDao.getById(idRequest.getId());
        if (byId != null && byId.size() == 1) {
            return ServiceResult.buildSuccessResult("查询成功", byId.get(0), idRequest);
        }
        return ServiceResult.buildFailedResult("查无此人", null, idRequest);
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<UserEntity> insert) {

        UserEntity data = insert.getData();
        data.preInsert(insert);
        int count = userDao.insert(data);
        if (count == 1) {
            return ServiceResult.buildSuccessResult("创建成功", count, insert);
        }
        return ServiceResult.buildFailedResult("创建失败", 0, insert);
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<UserEntity> update) {
        UserEntity data = update.getData();
        data.preUpdate(update);
        int count = userDao.update(data);
        if (count != 0) {
            return ServiceResult.buildSuccessResult("修改成功", count, update);
        } else {
            return ServiceResult.buildFailedResult("修改失败", 0, update);
        }
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        int delete = userDao.delete(idRequest.getId());
        if (delete != 0) {
            return ServiceResult.buildSuccessResult("删除成功", delete, idRequest);
        } else {
            return ServiceResult.buildFailedResult("删除失败", delete, idRequest);
        }
    }


    @Override
    public ServiceResult<UserEntity> getUserByIdNoToken(IdRequest idRequest) {

        //缓存
        UserEntity user = redisPoolUtil.getUser(idRequest.getToken());
        if (user != null) {
            return ServiceResult.buildSuccessResult("查询成功", user, idRequest);
        }

        List<UserEntity> byId = userDao.getById(idRequest.getId());
        if (byId != null && byId.size() == 1) {
            return ServiceResult.buildSuccessResult("查询成功", byId.get(0), idRequest);
        }
        return ServiceResult.buildFailedResult("查无此人", null, idRequest);
    }


    @Override
    public ServiceResult<String> getUserTokenNoToken(GetUserRequest userRequest) {
        String token = getToken(userRequest.getId(), userRequest.getUserType());
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
        String userType = tokenInfo_.substring(10, 11);


        String userId = tokenInfo_.substring(11, tokenInfo_.length() - 1 - salt.length());

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setDay(Integer.parseInt(day));
        tokenInfo.setHour(Integer.parseInt(hour));
        tokenInfo.setMin(Integer.parseInt(mon));
        tokenInfo.setSec(Integer.parseInt(sec));
        tokenInfo.setRandom(Integer.parseInt(random));
        try {
            tokenInfo.setType(UserTypeEnum.parse(Integer.parseInt(userType)));
        } catch (EnumParseNoHaveException e) {
            return ServiceResult.buildFailedResult(e.getMessage(), null, request);
        }
        tokenInfo.setUserId(userId);
        return ServiceResult.buildSuccessResult("解密成功", tokenInfo, request);
    }

    @Override
    public ServiceResult<LoginResponse> userLogin(LoginRequest userRequest) throws EnumParseNoHaveException {
        ArrayList<Arg> objects = new ArrayList<>();
        objects.add(new Arg("user_name", "=", userRequest.getUserName()));
        objects.add(new Arg("password", "=", MD5Util.MD5Encode(userRequest.getPassword())));
        objects.add(new Arg("user_type", "=", userRequest.getUserType().getUserType()));
        ArrayList<UserEntity> byArgsNoPage = userDao.getByArgsNoPage(objects);
        if (byArgsNoPage.size() != 1) {
            return ServiceResult.buildSuccessResult("登录失败,用户名或密码不正确", LoginResponse.buildLoginFail(), userRequest);
        }
        UserEntity userEntity = byArgsNoPage.get(0);

        String token = getToken(userEntity.getId(), UserTypeEnum.parse(userEntity.getUserType()));
        userRequest.setToken(token);
        if (userEntity != null) {
            redisPoolUtil.addUser(token, userEntity);
        }
        if (!userRequest.getUserType().equals(UserTypeEnum.USER)) { // 不是用户
            //获取角色所有的权限
            List<UserRightEntity> userRights = userDao.getUserRightsByUserId(userEntity.getId());
            //获取权限链
            List<UserRightEntity> list = initUserRightsParent(userRights);
            return ServiceResult.buildSuccessResult("登录成功", LoginResponse.buildLoginSuccess(token, userEntity, list), userRequest);
        } else {
            return ServiceResult.buildSuccessResult("登录成功", LoginResponse.buildLoginSuccess(token, userEntity, null), userRequest);
        }
    }

    @Override
    public ServiceResult<Boolean> registerNoToken(RegisterRequest registerRequest) {
        Integer nickNameRepete = userDao.checkRepeat(registerRequest.getNickName(), "nick_name");
        if (nickNameRepete != 0) {
            return ServiceResult.buildSuccessResult("昵称已存在", false, registerRequest);
        }
        Integer userNameRepete = userDao.checkRepeat(registerRequest.getUserName(), "user_name");
        if (userNameRepete != 0) {
            return ServiceResult.buildSuccessResult("用户名已存在", false, registerRequest);
        }
        return ServiceResult.buildSuccessResult("创建用户成功", true, registerRequest);

    }

    /**
     * 通过用户所有的叶子结点找出权限森林
     *
     * @param userRights
     * @return 所有的父节点(递归所有子节点)
     */
    private ArrayList<UserRightEntity> initUserRightsParent(List<UserRightEntity> userRights) {
        //获取此节点权限所有の父节点id
        TreeSet<String> treeSet = new TreeSet<>();
        for (UserRightEntity userRight : userRights) {
            String[] split = userRight.getParentIds().split(",");
            for (String s : split) {
                treeSet.add(s);
            }
        }

        //获取所有的关联节点
        HashMap<String, UserRightEntity> map = new HashMap<>();
        for (String userRightId : treeSet) {
            if (userRightId != "0") {
                if (!map.containsKey(userRightId)) {
                    UserRightEntity userRightEntity = userDao.getUserRightsByRightId(userRightId);
                    map.put(userRightId, userRightEntity);
                }
            }
        }
        for (UserRightEntity userRight : userRights) {
            if (!map.containsKey(userRight.getId())) {
                map.put(userRight.getId(), userRight);
            }
        }

        ArrayList<UserRightEntity> allParent = new ArrayList<>();
        for (Map.Entry<String, UserRightEntity> stringUserRightEntityEntry : map.entrySet()) {
            if (stringUserRightEntityEntry.getValue().getParentId().equals("0")) { // 选出父节点
                allParent.add(stringUserRightEntityEntry.getValue());
            }
        }
        //找出权限森林
        initChildren(allParent, map);
        return allParent;
    }

    private void initChildren(List<UserRightEntity> allParent, HashMap<String, UserRightEntity> map) {
        //父类遍历
        for (UserRightEntity userRightEntity : allParent) {
            //父类的子类准备
            List<UserRightEntity> chs = new ArrayList<>();
            //遍历所有类,把父类是这个类的子类添加到子类里
            for (Map.Entry<String, UserRightEntity> stringUserRightEntityEntry : map.entrySet()) {
                if (stringUserRightEntityEntry.getValue().getParentId().equals(userRightEntity.getId())) {
                    chs.add(stringUserRightEntityEntry.getValue());
                }
            }
            userRightEntity.setChds(chs);
            initChildren(chs, map);
        }

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


    private String getToken(String userId, UserTypeEnum userType) {
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

        //用户类型 1位
        sb.append(userType.getUserType());

        // 用户id 16位
        sb.append(userId);
        //盐 x位
        sb.append(salt);

        String token = AESUtil.AESEncode(encodeRules, sb.toString());

        return token;
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


}
