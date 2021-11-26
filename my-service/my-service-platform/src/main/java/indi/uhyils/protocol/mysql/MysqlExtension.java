package indi.uhyils.protocol.mysql;

import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.response.InvokeResponse;
import indi.uhyils.protocol.mysql.pojo.cqe.FindPasswordByNameQuery;
import indi.uhyils.protocol.mysql.pojo.cqe.InvokeCommand;
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
     * 执行
     *
     * @param command
     *
     * @return
     */
    ServiceResult<InvokeResponse> invoke(InvokeCommand command) throws Exception;

}
