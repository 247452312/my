package indi.uhyils.service.impl;

import indi.uhyils.enums.MysqlErrCodeEnum;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.enums.SqlTypeEnum;
import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.cqe.MysqlCommand;
import indi.uhyils.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.pojo.cqe.impl.MysqlSqlCommand;
import indi.uhyils.pojo.entity.VirtualNodeImpl;
import indi.uhyils.pojo.entity.node.Node;
import indi.uhyils.pojo.entity.user.MysqlUser;
import indi.uhyils.pojo.entity.user.User;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.ErrResponse;
import indi.uhyils.pojo.response.impl.OkResponse;
import indi.uhyils.pojo.response.impl.ResultSetResponse;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.repository.PlatformPublishNodeRepository;
import indi.uhyils.service.PlatformService;
import java.util.Collections;
import java.util.List;
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

    private final UserFacade userFacade;

    @Autowired
    private PlatformPublishNodeRepository publishNodeRepository;

    @Autowired
    private PlatformInternalNodeRepository internalNodeRepository;

    public PlatformServiceImpl(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

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

    @Override
    public List<MysqlResponse> query(MysqlSqlCommand command) {
        String sql = command.getSql();
        Node node = new VirtualNodeImpl(sql, command.getMysqlTcpInfo());
        // 最终结果
        NodeInvokeResult invoke = node.invoke(publishNodeRepository, internalNodeRepository);
        if (invoke == null || invoke.getJsonArray().isEmpty()) {
            return Collections.singletonList(new OkResponse(command.getMysqlTcpInfo()));
        }
        return Collections.singletonList(new ResultSetResponse(command.getMysqlTcpInfo(), invoke.getFieldInfos(), invoke.getJsonArray()));
    }

    @Override
    public List<MysqlResponse> findFieldList(MysqlCommand command) {
        return null;
    }

    @Override
    public List<MysqlResponse> findTableInfo(MysqlCommand command) {
        return null;
    }

}
