package indi.uhyils.protocol.mysql;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.protocol.mysql.pojo.cqe.FindPasswordByNameQuery;
import indi.uhyils.protocol.mysql.pojo.cqe.InvokePlanCommand;
import indi.uhyils.protocol.rpc.base.BaseProvider;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月17日 18时56分
 */
public interface MysqlExtension extends BaseProvider {

    /**
     * 根据一个消费者名称获取对应的信息
     *
     * @param query
     *
     * @return
     */
    ServiceResult<ConsumerInfoDTO> findPasswordByName(FindPasswordByNameQuery query);

    /**
     * 执行 执行计划
     *
     * @param command
     *
     * @return
     */
    ServiceResult<JSONArray> invokePlan(InvokePlanCommand command);

}
