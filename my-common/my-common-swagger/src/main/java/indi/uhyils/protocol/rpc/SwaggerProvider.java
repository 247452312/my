package indi.uhyils.protocol.rpc;

import indi.uhyils.annotation.Public;
import indi.uhyils.pojo.DTO.ClassSwaggerDTO;
import indi.uhyils.pojo.DTO.RpcClassSwaggerDTO;
import indi.uhyils.pojo.cqe.FindSwaggerQuery;
import indi.uhyils.protocol.rpc.base.BaseProvider;
import java.util.List;

/**
 * swagger解析
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时19分
 */
public interface SwaggerProvider extends BaseProvider {

    /**
     * 获取当前项目的swagger信息
     *
     * @param query
     *
     * @return
     */
    List<ClassSwaggerDTO> findSwagger(FindSwaggerQuery query);

    /**
     * 测试用,请忽略
     *
     * @param query
     *
     * @return
     */
    @Public
    List<RpcClassSwaggerDTO> findRpcSwagger(FindSwaggerQuery query);
}
