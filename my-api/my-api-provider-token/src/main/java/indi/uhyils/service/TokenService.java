package indi.uhyils.service;

import indi.uhyils.request.IdRequest;
import indi.uhyils.response.ServiceResult;

/**
 * 根据用户名获取token
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月26日 09时29分
 */
public interface TokenService {


    /**
     * token用处是用来获取单点登录服务器使用 此后这个token项目可以是单点登录服务器的原型
     * 使用AES加密
     * 根据当前时间+用户id+盐 生产的token
     * 长度在32位 ymmddhhmmss+用户名+5位盐的方式  32-16=16位用户id(MD5加密)
     *
     * @param userId 用户id
     * @return
     */
    ServiceResult<String> getToken(IdRequest userId);


}
