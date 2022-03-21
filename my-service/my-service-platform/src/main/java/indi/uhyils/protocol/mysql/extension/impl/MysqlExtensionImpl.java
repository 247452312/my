package indi.uhyils.protocol.mysql.extension.impl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.FindUserByNameQuery;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.response.LoginDTO;
import indi.uhyils.protocol.mysql.extension.MysqlExtension;
import indi.uhyils.util.CollectionUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * mysql自定义扩展点实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月17日 19时25分
 */
@Component
public class MysqlExtensionImpl implements MysqlExtension {

    @Autowired
    private UserFacade userFacade;

    @Override
    @NoToken
    public ServiceResult<UserDTO> findPasswordByName(FindUserByNameQuery query) {
        List<UserDTO> consumerInfoDTOS = userFacade.getUserByUsername(query);
        if (CollectionUtil.isEmpty(consumerInfoDTOS)) {
            return ServiceResult.buildSuccessResult(null);
        }
        UserDTO consumerInfoDTO = consumerInfoDTOS.get(0);
        return ServiceResult.buildSuccessResult(consumerInfoDTO);
    }

//    @Override
//    public ServiceResult<InvokeResponse> invoke(InvokeCommand command) throws Exception {
//        String tableName = command.getTableName();
//        List<InterfaceInfoDTO> interfaceInfoDTOS = interfaceInfoService.queryNoPage(Collections.singletonList(Arg.as(InterfaceInfoDO::getName, Symbol.EQ, tableName)), null);
//        Asserts.assertTrue(interfaceInfoDTOS != null, "查询表名错误:无表名");
//        Asserts.assertTrue(interfaceInfoDTOS.size() == 1, "查询表名错误:表名不止一个,或者为空");
//        InterfaceInfoDTO interfaceInfoDTO = interfaceInfoDTOS.get(0);
//
//        JSON json = interfaceInfoService.invokeInterface(InvokeInterfaceCommand.build(interfaceInfoDTO.getId(), command.getConsumerId(), command.getParams()));
//        if (json instanceof JSONObject) {
//            JSONObject resultJson = (JSONObject) json;
//            json = new JSONArray();
//            ((JSONArray) json).add(resultJson);
//        }
//        return ServiceResult.buildSuccessResult(InvokeResponse.build((JSONArray) json));
//    }

    @Override
    public ServiceResult<LoginDTO> login(LoginCommand loginCommand) {
        LoginDTO loginDTO = userFacade.login(loginCommand);
        return ServiceResult.buildSuccessResult(loginDTO);
    }
}
