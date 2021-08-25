package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.DO.UserDO;


/**
 * 用户entity,Do转换
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 18时14分
 */
@Convert
public class UserConvert extends AbstractDoConvert<User, UserDO> {


    @Override
    public User doToEntity(UserDO dO) {
        return new User(dO);
    }
}
