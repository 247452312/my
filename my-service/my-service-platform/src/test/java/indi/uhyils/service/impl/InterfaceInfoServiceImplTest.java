package indi.uhyils.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.BaseTest;
import indi.uhyils.pojo.cqe.command.InvokeInterfaceCommand;
import indi.uhyils.service.InterfaceInfoService;
import java.util.HashMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月28日 11时08分
 */
public class InterfaceInfoServiceImplTest extends BaseTest {


    @Autowired
    private InterfaceInfoService service;

    @Test
    public void invokeInterface() throws Exception {
        InvokeInterfaceCommand command = new InvokeInterfaceCommand();
        command.setInterfaceId(1L);
        HashMap<String, Object> param = new HashMap<>();
        command.setParam(param);
        command.setConsumerId(1L);

        JSON json = service.invokeInterface(command);
        System.out.println(json);

    }
}
