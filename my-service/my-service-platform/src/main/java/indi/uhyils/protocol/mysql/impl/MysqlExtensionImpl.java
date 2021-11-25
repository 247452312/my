package indi.uhyils.protocol.mysql.impl;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.protocol.mysql.MysqlExtension;
import indi.uhyils.protocol.mysql.pojo.cqe.FindPasswordByNameQuery;
import indi.uhyils.protocol.mysql.pojo.cqe.InvokePlanCommand;
import indi.uhyils.service.ConsumerInfoService;
import indi.uhyils.util.CollectionUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * mysql自定义扩展点
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
    private

    @Override
    public ServiceResult<ConsumerInfoDTO> findPasswordByName(FindPasswordByNameQuery query) {
        List<ConsumerInfoDTO> consumerInfoDTOS = consumerInfoService.queryNoPage(query.getArgs(), query.getOrder());
        if (CollectionUtil.isEmpty(consumerInfoDTOS)) {
            return ServiceResult.buildSuccessResult(null);
        }
        ConsumerInfoDTO consumerInfoDTO = consumerInfoDTOS.get(0);
        return ServiceResult.buildSuccessResult(consumerInfoDTO);
    }

    @Override
    public ServiceResult<JSONArray> invokePlan(InvokePlanCommand command) {
        return ServiceResult.buildSuccessResult(null);
    }
}
