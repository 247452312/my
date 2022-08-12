package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.DTO.LoginDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.request.ApplyUserCommand;
import indi.uhyils.pojo.DTO.request.FindUserByNameQuery;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.request.UpdatePasswordCommand;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.BlankCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;


/**
 * 用户接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时29分
 */
public interface UserProvider extends DTOProvider<UserDTO> {


    /**
     * 注入信息用,一般不用
     *
     * @param request 用户的idi
     *
     * @return 用户
     */
    UserDTO getUserById(IdQuery request);


    /**
     * token用处是用来获取单点登录服务器使用 此后这个token项目可以是单点登录服务器的原型
     * 使用AES加密
     * 根据当前时间+用户id+盐 生产的token
     * <p>
     * token规则修改-> token有效时间设置为1个小时, 所以不需要加入年月,可以保留天
     * 规则修改为  ddhhmmss + 2位随机数(防重复) + 用户类型 + userId + salt 共8+2+1+16+3 = 30位
     * 如果是游客, 则userId随机生成一个16位数字序列作为这个游客的id
     *
     * @param request 用户id
     *
     * @return 通过用户id和用户类型编译的token
     */
    String getUserToken(IdQuery request);


    /**
     * 根据token 获取token中包含的信息
     * 解析token
     *
     * @param request 默认的信息
     *
     * @return 解析后的token数据
     */
    TokenInfo getTokenInfoByToken(DefaultCQE request);


    /**
     * 用户登录 包含从哪里登录的信息
     *
     * @param request 用户登录所需要的条件
     *
     * @return 登录所需要的信息
     */
    LoginDTO login(LoginCommand request);

    /**
     * 游客登录
     *
     * @param request 游客登录所需要的条件
     *
     * @return
     */
    LoginDTO visiterLogin(BlankCommand request);

    /**
     * 登出(删除redis中的用户)
     *
     * @param request 无参数
     *
     * @return 是否登出成功
     */
    Boolean logout(DefaultCQE request);

    /**
     * 获取全部用户
     *
     * @param request 默认请求
     *
     * @return 全部用户
     */
    List<UserDTO> getUsers(DefaultCQE request);

    /**
     * 默认获取用户本身的方式
     *
     * @param request 默认请求
     *
     * @return 用户
     */
    UserDTO getUserByToken(DefaultCQE request);

    /**
     * 更新密码
     *
     * @param request 修改密码请求
     *
     * @return 修改密码的返回
     */
    String updatePassword(UpdatePasswordCommand request);

    /**
     * 根据id获取用户名称
     *
     * @param request id
     *
     * @return 用户名称
     */
    String getNameById(IdQuery request);

    /**
     * 批量根据id获取用户
     *
     * @param request
     *
     * @return
     */
    List<UserDTO> getSampleUserByIds(IdsQuery request);


    /**
     * 申请一个用户
     *
     * @param request
     *
     * @return
     */
    Boolean applyUser(ApplyUserCommand request);


    /**
     * 通过一个用户的申请
     *
     * @param request
     *
     * @return
     */
    Boolean passApply(IdCommand request);

    /**
     * 停用一个用户
     *
     * @param request
     *
     * @return
     */
    Boolean stopUser(IdCommand request);

    /**
     * 根据用户名获取用户信息
     *
     * @param request 用户名 username
     *
     * @return
     */
    UserDTO getUserByUserName(FindUserByNameQuery request);
}
