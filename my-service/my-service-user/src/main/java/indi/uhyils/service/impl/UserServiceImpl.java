package indi.uhyils.service.impl;

import indi.uhyils.dao.UserDao;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.request.LoginRequest;
import indi.uhyils.pojo.DTO.request.UpdatePasswordRequest;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.response.LoginResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.service.UserService;
import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时28分
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao dao;

    @Value("${token.salt}")
    private String salt;

    @Value("${token.encodeRules}")
    private String encodeRules;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DeptRepository deptRepository;


    @Autowired
    private PowerRepository powerRepository;

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public ServiceResult<UserDO> getUserById(IdRequest idRequest) {
        return null;
    }

    @Override
    public ServiceResult<String> getUserToken(IdRequest request) {
        return null;
    }

    @Override
    public ServiceResult<TokenInfo> getTokenInfoByToken(DefaultRequest request) {
        return null;
    }

    @Override
    public ServiceResult<LoginResponse> login(LoginRequest userRequest) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> logout(DefaultRequest request) {
        return null;
    }

    @Override
    public ServiceResult<ArrayList<UserDO>> getUsers(DefaultRequest request) {
        return null;
    }

    @Override
    public ServiceResult<UserDO> getUserByToken(DefaultRequest request) {
        return null;
    }

    @Override
    public ServiceResult<String> updatePassword(UpdatePasswordRequest request) {
        return null;
    }

    @Override
    public ServiceResult<String> getNameById(IdRequest request) {
        return null;
    }
}
