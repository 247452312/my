package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.ApiGroupDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;

/**
 * api组表(ApiGroup)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分51秒
 */
public interface ApiGroupProvider extends DTOProvider<ApiGroupDTO> {

    /**
     * 测试api
     *
     * @param request api id
     *
     * @return 结果
     */
    ServiceResult<String> test(IdQuery request);

    /**
     * 获取可以被订阅的所有api群
     *
     * @param request 默认请求
     *
     * @return 可以被订阅的api群
     */
    ServiceResult<List<ApiGroupDTO>> getCanBeSubscribed(DefaultCQE request);
}

