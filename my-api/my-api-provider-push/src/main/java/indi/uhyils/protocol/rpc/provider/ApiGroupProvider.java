package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.ApiGroupDO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.ArrayList;

/**
 * 外界api调用表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时22分
 */
public interface ApiGroupProvider extends DTOProvider<ApiGroupDO> {

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
    ServiceResult<ArrayList<ApiGroupDO>> getCanBeSubscribed(DefaultCQE request);


}
