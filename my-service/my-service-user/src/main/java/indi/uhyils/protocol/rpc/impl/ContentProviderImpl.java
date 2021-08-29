package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ContentDTO;
import indi.uhyils.protocol.rpc.ContentProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公共参数(Content)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分19秒
 */
@RpcService
public class ContentProviderImpl extends BaseDefaultProvider<ContentDTO> implements ContentProvider {


    @Autowired
    private ContentService service;


    @Override
    protected BaseDoService<ContentDTO> getService() {
        return service;
    }

}

