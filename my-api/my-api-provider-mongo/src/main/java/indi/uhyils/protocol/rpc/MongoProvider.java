package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.request.NameRequest;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.protocol.rpc.base.BaseProvider;

/**
 * mongo接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 06时35分
 */
public interface MongoProvider extends BaseProvider {

    /**
     * 添加
     *
     * @param request base64 图片
     *
     * @return fileName
     */
    ServiceResult<String> add(AddCommand<String> request);

    /**
     * 删除
     *
     * @param request fileName
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> delete(NameRequest request);


    /**
     * 根据fileName精准查询
     *
     * @param request fileName
     *
     * @return base64
     */
    ServiceResult<String> getByFileName(NameRequest request);
}
