package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderInfoDao;
import indi.uhyils.pojo.model.OrderInfoEntity;
import indi.uhyils.service.OrderInfoService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderInfoServiceImpl extends BaseDefaultServiceImpl<OrderInfoEntity> implements OrderInfoService {

    @Resource
    private OrderInfoDao dao;


    public OrderInfoDao getDao() {
        return dao;
    }

    public void setDao(OrderInfoDao dao) {
        this.dao = dao;
    }
}
