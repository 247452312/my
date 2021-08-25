package indi.uhyils.serviceImpl;

import indi.uhyils.dao.SendPageDao;
import indi.uhyils.pojo.DO.SendPageDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * 页面推送消息接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
public class SendPageProvider extends BaseDefaultProvider<SendPageDO> implements indi.uhyils.protocol.rpc.provider.SendPageProvider {

    @Resource
    private SendPageDao dao;

    @Override
    public SendPageDao getDao() {
        return dao;
    }

    public void setDao(SendPageDao dao) {
        this.dao = dao;
    }
}
