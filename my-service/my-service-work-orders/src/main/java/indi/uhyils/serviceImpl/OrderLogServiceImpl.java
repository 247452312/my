package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderLogDao;
import indi.uhyils.pojo.model.OrderLogEntity;
import indi.uhyils.service.OrderLogService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderLogServiceImpl extends BaseDefaultServiceImpl<OrderLogEntity> implements OrderLogService {

    @Resource
    private OrderLogDao dao;


    @Override
    public OrderLogDao getDao() {
        return dao;
    }

    public void setDao(OrderLogDao dao) {
        this.dao = dao;
    }
}
