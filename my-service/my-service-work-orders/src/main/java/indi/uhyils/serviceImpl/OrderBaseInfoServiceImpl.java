package indi.uhyils.serviceImpl;

import org.apache.dubbo.config.annotation.Service;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.content.Content;
import indi.uhyils.dao.OrderBaseInfoDao;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.*;
import indi.uhyils.service.OrderBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderBaseInfoServiceImpl extends BaseDefaultServiceImpl<OrderBaseInfoEntity> implements OrderBaseInfoService {

    @Autowired
    private OrderBaseInfoDao dao;


    public OrderBaseInfoDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseInfoDao dao) {
        this.dao = dao;
    }
}
