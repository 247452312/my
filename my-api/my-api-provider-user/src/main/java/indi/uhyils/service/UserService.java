package indi.uhyils.service;

import indi.uhyils.exception.EnumParseNoHaveException;
import indi.uhyils.model.TokenInfo;
import indi.uhyils.model.UserEntity;
import indi.uhyils.request.DefaultRequest;
import indi.uhyils.request.GetUserRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.LoginRequest;
import indi.uhyils.response.LoginResponse;
import indi.uhyils.response.ServiceResult;


/**
 * 用户接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时29分
 */
public interface UserService extends DefaultEntityService<UserEntity> {


    /**
     * 游客第一次登录 注入游客信息用
     *
     * @param idRequest 包含自动生成的16位用户id
     * @return
     */
    ServiceResult<UserEntity> getUserByIdNoToken(IdRequest idRequest);


    /**
     * token用处是用来获取单点登录服务器使用 此后这个token项目可以是单点登录服务器的原型
     * 使用AES加密
     * 根据当前时间+用户id+盐 生产的token
     * <p>
     * token规则修改-> token有效时间设置为1个小时, 所以不需要加入年月,可以保留天
     * 规则修改为  ddhhmmss + 2位随机数(防重复) + 用户类型 + userId + salt 共8+2+1+16+3 = 30位
     * 如果是游客, 则userId随机生成一个16位数字序列作为这个游客的id
     *
     * @param userRequest 用户id
     * @return
     */
    ServiceResult<String> getUserTokenNoToken(GetUserRequest userRequest);


    /**
     * 根据token 获取token中包含的信息
     * 解析token
     *
     * @param request 默认的信息
     * @return
     */
    ServiceResult<TokenInfo> getTokenInfoByTokenNoToken(DefaultRequest request);


    /**
     * 用户登录 包含从哪里登录的信息
     *
     * @param userRequest
     * @return
     */
    ServiceResult<LoginResponse> userLogin(LoginRequest userRequest) throws EnumParseNoHaveException;

}
