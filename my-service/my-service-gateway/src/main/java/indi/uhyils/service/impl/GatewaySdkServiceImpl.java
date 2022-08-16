package indi.uhyils.service.impl;

import indi.uhyils.annotation.Public;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.enums.SqlTypeEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.pojo.entity.Company;
import indi.uhyils.repository.CompanyRepository;
import indi.uhyils.service.GatewaySdkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月16日 08时23分
 */
@Service
public class GatewaySdkServiceImpl implements GatewaySdkService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Public
    public Object invoke(InvokeCommand command) {
        return command;
    }

    @Override
    public MysqlResponse login(MysqlAuthCommand command) {
        MysqlTcpInfo mysqlTcpInfo = command.getMysqlTcpInfo();
        Company company = new Company(command);

        // 0.查询用户
        company.completionByAk(companyRepository);

        // 1.判断密码是否正确
        if (company.checkSkByMysqlChallenge(command.getMysqlTcpInfo().getRandomByte(), command.getChallenge())) {
            return new OkResponse(mysqlTcpInfo, SqlTypeEnum.NULL);
        }
        return new ErrResponse(mysqlTcpInfo, MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "密码错误,密码请使用secretKey");
    }
}
