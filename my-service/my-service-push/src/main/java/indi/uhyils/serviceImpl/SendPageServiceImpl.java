package indi.uhyils.serviceImpl;

import indi.uhyils.dao.SendPageDao;
import indi.uhyils.pojo.model.SendPageDO;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.SendPageService;

import javax.annotation.Resource;

/**
 * 页面推送消息接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
public class SendPageServiceImpl extends BaseDefaultServiceImpl<SendPageDO> implements SendPageService {

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
