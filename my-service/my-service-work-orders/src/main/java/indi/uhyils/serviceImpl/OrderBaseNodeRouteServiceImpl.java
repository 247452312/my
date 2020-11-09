package indi.uhyils.serviceImpl;

import org.apache.dubbo.config.annotation.Service;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.content.Content;
import indi.uhyils.dao.OrderBaseNodeRouteDao;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.*;
import indi.uhyils.service.OrderBaseNodeRouteService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderBaseNodeRouteServiceImpl extends BaseDefaultServiceImpl<OrderBaseNodeRouteEntity> implements OrderBaseNodeRouteService {

    @Autowired
    private OrderBaseNodeRouteDao dao;


    public OrderBaseNodeRouteDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseNodeRouteDao dao) {
        this.dao = dao;
    }
}
