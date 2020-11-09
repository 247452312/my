package indi.uhyils.serviceImpl;

import org.apache.dubbo.config.annotation.Service;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.content.Content;
import indi.uhyils.dao.OrderNodeRouteDao;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.*;
import indi.uhyils.service.OrderNodeRouteService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderNodeRouteServiceImpl extends BaseDefaultServiceImpl<OrderNodeRouteEntity> implements OrderNodeRouteService {

    @Autowired
    private OrderNodeRouteDao dao;


    public OrderNodeRouteDao getDao() {
        return dao;
    }

    public void setDao(OrderNodeRouteDao dao) {
        this.dao = dao;
    }
}
