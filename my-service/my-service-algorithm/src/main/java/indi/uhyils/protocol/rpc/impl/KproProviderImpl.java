package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.DbInformation;
import indi.uhyils.pojo.DTO.request.ProjectGenerateRequest;
import indi.uhyils.protocol.rpc.KproProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.KproService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 21时06分
 */
@RpcService
public class KproProviderImpl implements KproProvider {

    @Autowired
    private KproService service;

    @Override
    public ServiceResult<Map<String, String>> projectGenerate(ProjectGenerateRequest request) {
        List<DbInformation> list = request.getList();
        Map<String, String> result = service.projectGenerate(list);
        return ServiceResult.buildSuccessResult(result);
    }
}
