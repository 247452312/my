package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.ShopDao;
import indi.uhyils.pojo.model.ShopEntity;
import indi.uhyils.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时08分
 */
@Service
public class ShopServiceImpl extends DefaultServiceImpl<ShopEntity> implements ShopService {
    @Autowired
    private ShopDao dao;

    public ShopDao getDao() {
        return dao;
    }

    public void setDao(ShopDao dao) {
        this.dao = dao;
    }
}
