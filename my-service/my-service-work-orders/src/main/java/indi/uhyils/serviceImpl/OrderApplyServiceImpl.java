package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderApiDao;
import indi.uhyils.dao.OrderApplyDao;
import indi.uhyils.pojo.model.OrderApplyEntity;
import indi.uhyils.service.OrderApplyService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderApplyServiceImpl extends BaseDefaultServiceImpl<OrderApplyEntity> implements OrderApplyService {

    @Resource
    private OrderApplyDao dao;


    public OrderApplyDao getDao() {
        return dao;
    }

    public void setDao(OrderApplyDao dao) {
        this.dao = dao;
    }
}
