package indi.uhyils.service;

import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.response.LoginDTO;
import indi.uhyils.pojo.entity.Token;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.Password;
import indi.uhyils.pojo.entity.type.UserName;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时27分
 */
public interface UserService extends BaseDoService<UserDTO> {

    /**
     * 注入信息用,一般不用
     *
     * @param userId 用户的id
     *
     * @return 用户
     */
    UserDTO getUserById(Identifier userId);


    /**
     * token用处是用来获取单点登录服务器使用 此后这个token项目可以是单点登录服务器的原型
     * 使用AES加密
     * 根据当前时间+用户id+盐 生产的token
     * <p>
     * token规则修改-> token有效时间设置为1个小时, 所以不需要加入年月,可以保留天
     * 规则修改为  ddhhmmss + 2位随机数(防重复) + 用户类型 + userId + salt 共8+2+1+16+3 = 30位
     * 如果是游客, 则userId随机生成一个16位数字序列作为这个游客的id
     *
     * @param userId 用户id
     *
     * @return 通过用户id和用户类型编译的token
     */
    String getUserToken(Identifier userId);


    /**
     * 根据token 获取token中包含的信息
     * 解析token
     *
     * @return 解析后的token数据
     */
    TokenInfo getTokenInfoByToken(Token token);


    /**
     * 用户登录 包含从哪里登录的信息
     *
     * @param userName 用户名
     * @param password 密码
     *
     * @return 登录所需要的信息
     */
    LoginDTO login(UserName userName, Password password);

    /**
     * 登出(删除redis中的用户)
     *
     * @return 是否登出成功
     */
    Boolean logout();

    /**
     * 获取全部用户
     *
     * @return 全部用户
     */
    List<UserDTO> getUsers();

    /**
     * 默认获取用户本身的方式
     *
     * @return 用户
     */
    UserDTO getUserByToken();

    /**
     * 更新密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     *
     * @return 修改密码的返回
     */
    String updatePassword(Password oldPassword, Password newPassword);

    /**
     * 根据id获取用户名称
     *
     * @param userId id
     *
     * @return 用户名称
     */
    String getNameById(Identifier userId);

    /**
     * 根据id批量获取不填充角色的用户
     *
     * @param userIds
     *
     * @return
     */
    List<UserDTO> getSampleUserByIds(List<Identifier> userIds);

    /**
     * 使用传过来的用户名密码强制登录,一般用于系统内其他协议进行登录
     *
     * @param username 用户名
     * @param password 密码
     *
     * @return
     */
    LoginDTO forceLogin(UserName username, Password password);
}
