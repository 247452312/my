package indi.uhyils.serviceImpl;

import org.apache.dubbo.config.annotation.Service;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.content.Content;
import indi.uhyils.dao.OrderFlowSideDao;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.*;
import indi.uhyils.service.OrderFlowSideService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月27日 01时06分
 */
@Service(group = "${spring.profiles.active}")
public class OrderFlowSideServiceImpl extends BaseDefaultServiceImpl<OrderFlowSideEntity> implements OrderFlowSideService {

    @Autowired
    private OrderFlowSideDao dao;


    public OrderFlowSideDao getDao() {
        return dao;
    }

    public void setDao(OrderFlowSideDao dao) {
        this.dao = dao;
    }
}
