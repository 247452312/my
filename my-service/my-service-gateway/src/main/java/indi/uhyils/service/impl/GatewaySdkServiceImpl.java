package indi.uhyils.service.impl;

import indi.uhyils.annotation.Public;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.service.GatewaySdkService;
import org.springframework.stereotype.Service;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月16日 08时23分
 */
@Service
public class GatewaySdkServiceImpl implements GatewaySdkService {

    @Override
    @Public
    public Object invoke(InvokeCommand command) {
        return command;
    }
}
