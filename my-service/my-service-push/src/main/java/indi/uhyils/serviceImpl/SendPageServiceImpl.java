package indi.uhyils.serviceImpl;

import indi.uhyils.dao.SendPageDao;
import indi.uhyils.pojo.model.SendPageEntity;
import indi.uhyils.service.SnedPageService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 页面推送消息接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@Service(group = "${spring.profiles.active}")
public class SendPageServiceImpl extends BaseDefaultServiceImpl<SendPageEntity> implements SnedPageService {

    @Autowired
    private SendPageDao dao;

    public SendPageDao getDao() {
        return dao;
    }

    public void setDao(SendPageDao dao) {
        this.dao = dao;
    }
}
