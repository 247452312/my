package indi.uhyils.service.impl;

import indi.uhyils.enums.MysqlErrCodeEnum;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.enums.SqlTypeEnum;
import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.pojo.entity.user.MysqlUser;
import indi.uhyils.pojo.entity.user.User;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.ErrResponse;
import indi.uhyils.pojo.response.impl.OkResponse;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对接平台主service
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月23日 09时05分
 */
@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private UserFacade userFacade;

    @Override
    public MysqlResponse login(MysqlAuthCommand mysqlCommand) {
        MysqlTcpInfo mysqlTcpInfo = mysqlCommand.getMysqlTcpInfo();
        User mysqlUser = new MysqlUser(mysqlCommand);

        // 0.查询用户
        mysqlUser.findUserByUserName(userFacade);

        // 1.判断密码是否正确
        boolean passwordRight = mysqlUser.checkPassword(userFacade, mysqlCommand.getChallenge());
        if (passwordRight) {
            // 密码正确就登录
            boolean loginSuccess = mysqlUser.login(userFacade);
            if (loginSuccess) {
                return new OkResponse(mysqlTcpInfo, SqlTypeEnum.NULL);
            }
        }
        return new ErrResponse(mysqlTcpInfo, MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "密码错误,密码请使用secretKey");
    }

}
