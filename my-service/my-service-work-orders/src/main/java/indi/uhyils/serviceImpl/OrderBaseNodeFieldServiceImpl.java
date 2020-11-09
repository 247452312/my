package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseNodeFieldDao;
import indi.uhyils.pojo.model.OrderBaseNodeFieldEntity;
import indi.uhyils.service.OrderBaseNodeFieldService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderBaseNodeFieldServiceImpl extends BaseDefaultServiceImpl<OrderBaseNodeFieldEntity> implements OrderBaseNodeFieldService {

    @Autowired
    private OrderBaseNodeFieldDao dao;


    public OrderBaseNodeFieldDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseNodeFieldDao dao) {
        this.dao = dao;
    }
}
