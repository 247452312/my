package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PushMsgDTO;
import indi.uhyils.pojo.DTO.request.CronRequest;
import indi.uhyils.pojo.DTO.request.PushMsgToSomeoneRequest;
import indi.uhyils.protocol.rpc.PushMsgProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PushMsgService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 推送日志表(PushMsg)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分05秒
 */
@RpcService
public class PushMsgProviderImpl extends BaseDefaultProvider<PushMsgDTO> implements PushMsgProvider {


    @Autowired
    private PushMsgService service;

    @Override
    public Boolean push(CronRequest request) {
        return service.push(request);
    }

    @Override
    public Boolean pushMsgToSomeone(PushMsgToSomeoneRequest request) {
        return service.pushMsgToSomeone(request);
    }

    @Override
    protected BaseDoService<PushMsgDTO> getService() {
        return service;
    }
}

