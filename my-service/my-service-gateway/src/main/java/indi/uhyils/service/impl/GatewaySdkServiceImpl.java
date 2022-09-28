package indi.uhyils.service.impl;

import indi.uhyils.annotation.Public;
import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.enums.SqlTypeEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.pojo.DTO.DatabaseInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.pojo.cqe.query.BlackQuery;
import indi.uhyils.pojo.dto.response.GetInterfaceInfoResponse;
import indi.uhyils.pojo.entity.AbstractDataNode;
import indi.uhyils.pojo.entity.CallNode;
import indi.uhyils.pojo.entity.Company;
import indi.uhyils.pojo.entity.ProviderInterface;
import indi.uhyils.pojo.entity.SysProviderInterface;
import indi.uhyils.repository.CallNodeRepository;
import indi.uhyils.repository.CompanyRepository;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceParamRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.service.GatewaySdkService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.GatewayUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javafx.util.Pair;
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

    @Autowired
    private CallNodeRepository callNodeRepository;

    @Override
    @Public
    public NodeInvokeResult invokeInterface(InvokeCommand command) {

        final Pair<String, String> splitDataBaseUrl = GatewayUtil.splitDataBaseUrl(command.getPath());
        final ProviderInterface providerInterface = providerInterfaceRepository.find(command.getInvokeType(), splitDataBaseUrl.getKey(), splitDataBaseUrl.getValue());
        providerInterface.fillParams(providerInterfaceParamRepository);
        //        JSONArray.parseArray(JSONObject.toJSONString(Arrays.asList(command)))
        return new NodeInvokeResult();
    }

    @Override
    public NodeInvokeResult invokeNode(InvokeCommand command) {
        final String path = command.getPath();
        Boolean isSysTable = nodeRepository.judgeSysTable(path);
        if (isSysTable) {
            AbstractDataNode providerInterface = new SysProviderInterface(command.getPath(), command.getHeader(), command.getParams());
            return providerInterface.getResult();
        } else {
            final Pair<String, String> splitDataBaseUrl = GatewayUtil.splitDataBaseUrl(path);
            AbstractDataNode node = callNodeRepository.findNodeByDatabaseAndTable(splitDataBaseUrl.getKey(), splitDataBaseUrl.getValue());
            Asserts.assertTrue(node != null, "未查询到指定的节点,名称:{}", command.getPath());
            return node.getResult();
        }

    }

    @Override
    public MysqlResponse login(MysqlAuthCommand command) {
        MysqlTcpInfo mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
        Company company = new Company(command);

        // 0.查询用户
        company.completionByAk(companyRepository);

        // 1.判断密码是否正确
        if (company.checkSkByMysqlChallenge(mysqlTcpInfo.getRandomByte(), command.getChallenge())) {
            final UserDTO userDTO = new UserDTO();
            userDTO.setIp(UserInfoHelper.getUserIp().orElse(null));
            userDTO.setId(company.unique.getId());
            mysqlTcpInfo.setUserDTO(userDTO);
            return new OkResponse(SqlTypeEnum.NULL);
        }
        return new ErrResponse(MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "密码错误,密码请使用secretKey");
    }

    @Override
    public List<DatabaseInfo> getAllDatabaseInfo(BlackQuery blackQuery) {
        final UserDTO userDTO = UserInfoHelper.get().orElseThrow(() -> Asserts.makeException("未登录"));
        Asserts.assertTrue(userDTO != null, "未登录");
        List<CallNode> callNodes = callNodeRepository.findByUser(userDTO);
        return new ArrayList<>(callNodes.stream()
                                        .map(CallNode::changeToDatabaseInfo)
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toMap(DatabaseInfo::getSchemaName, t -> t, (key1, key2) -> key2))
                                        .values());
    }

    /**
     * todo 待删除 逻辑迁移到逻辑中去
     *
     * @param command
     *
     * @return
     */
    private GetInterfaceInfoResponse getInterfaceInfo(InvokeCommand command) {
        final String path = command.getPath();
        final Integer invokeType = command.getInvokeType();

        final Pair<String, String> splitDataBaseUrl = GatewayUtil.splitDataBaseUrl(path);
        ProviderInterface providerInterface = providerInterfaceRepository.find(invokeType, splitDataBaseUrl.getKey(), splitDataBaseUrl.getValue());
        providerInterface.fillParams(providerInterfaceParamRepository);
        return GetInterfaceInfoResponse.build(providerInterface.fieldInfo());
    }
}
