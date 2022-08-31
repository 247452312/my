package indi.uhyils.service.impl;

import indi.uhyils.annotation.Public;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.enums.SqlTypeEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.pojo.dto.response.GetInterfaceInfoResponse;
import indi.uhyils.pojo.entity.AbstractDataNode;
import indi.uhyils.pojo.entity.Company;
import indi.uhyils.pojo.entity.ProviderInterface;
import indi.uhyils.repository.CompanyRepository;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceParamRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
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

    @Autowired
    private ProviderInterfaceRepository providerInterfaceRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ProviderInterfaceParamRepository providerInterfaceParamRepository;

    @Override
    @Public
    public NodeInvokeResult invokeInterface(InvokeCommand command) {
        final ProviderInterface providerInterface = providerInterfaceRepository.find(command.getInvokeType(), command.getPath());
        providerInterface.fillParams(providerInterfaceParamRepository);
        //        JSONArray.parseArray(JSONObject.toJSONString(Arrays.asList(command)))
        return new NodeInvokeResult();
    }

    @Override
    public NodeInvokeResult invokeNode(InvokeCommand command) {
        AbstractDataNode node = nodeRepository.find(command.getPath());
        return node.getResult();
    }

    @Override
    public GetInterfaceInfoResponse getInterfaceInfo(InvokeCommand command) {
        final String path = command.getPath();
        final Integer invokeType = command.getInvokeType();
        ProviderInterface providerInterface = providerInterfaceRepository.find(invokeType, path);
        providerInterface.fillParams(providerInterfaceParamRepository);
        return GetInterfaceInfoResponse.build(providerInterface.fieldInfo());
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
