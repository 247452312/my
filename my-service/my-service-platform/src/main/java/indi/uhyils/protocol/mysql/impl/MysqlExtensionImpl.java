package indi.uhyils.protocol.mysql.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.enum_.Symbol;
import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.DTO.InterfaceInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.response.LoginDTO;
import indi.uhyils.pojo.cqe.command.InvokeInterfaceCommand;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.response.InvokeResponse;
import indi.uhyils.protocol.mysql.MysqlExtension;
import indi.uhyils.protocol.mysql.pojo.cqe.FindPasswordByNameQuery;
import indi.uhyils.protocol.mysql.pojo.cqe.InvokeCommand;
import indi.uhyils.service.ConsumerInfoService;
import indi.uhyils.service.InterfaceInfoService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.util.Collections;
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
    private ConsumerInfoService consumerInfoService;

    @Autowired
    private InterfaceInfoService interfaceInfoService;

    @Autowired
    private UserFacade userFacade;

    @Override
    @NoToken
    public ServiceResult<ConsumerInfoDTO> findPasswordByName(FindPasswordByNameQuery query) {
        List<ConsumerInfoDTO> consumerInfoDTOS = consumerInfoService.queryNoPage(query.getArgs(), query.getOrder());
        if (CollectionUtil.isEmpty(consumerInfoDTOS)) {
            return ServiceResult.buildSuccessResult(null);
        }
        ConsumerInfoDTO consumerInfoDTO = consumerInfoDTOS.get(0);
        return ServiceResult.buildSuccessResult(consumerInfoDTO);
    }


    @Override
    public ServiceResult<InvokeResponse> invoke(InvokeCommand command) throws Exception {
        String tableName = command.getTableName();
        List<InterfaceInfoDTO> interfaceInfoDTOS = interfaceInfoService.queryNoPage(Collections.singletonList(Arg.as(InterfaceInfoDO::getName, Symbol.EQ, tableName)), null);
        Asserts.assertTrue(interfaceInfoDTOS != null, "查询表名错误:无表名");
        Asserts.assertTrue(interfaceInfoDTOS.size() == 1, "查询表名错误:表名不止一个");
        InterfaceInfoDTO interfaceInfoDTO = interfaceInfoDTOS.get(0);

        JSON json = interfaceInfoService.invokeInterface(InvokeInterfaceCommand.build(interfaceInfoDTO.getId(), command.getConsumerId(), command.getParams()));
        if (json instanceof JSONObject) {
            JSONObject resultJson = (JSONObject) json;
            json = new JSONArray();
            ((JSONArray) json).add(resultJson);
        }
        return ServiceResult.buildSuccessResult(InvokeResponse.build((JSONArray) json));
    }

    @Override
    public ServiceResult<LoginDTO> login(LoginCommand loginCommand) {
        LoginDTO loginDTO = userFacade.forceLogin(loginCommand);
        return ServiceResult.buildSuccessResult(loginDTO);
    }
}
