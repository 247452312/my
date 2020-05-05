package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.GoodDao;
import indi.uhyils.dao.ShopDao;
import indi.uhyils.model.ShopEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时08分
 */
@Service
public class ShopServiceImpl extends DefaultServiceImpl<ShopEntity> implements ShopService{
    @Autowired
    private ShopDao dao;

    public ShopDao getDao() {
        return dao;
    }

    public void setDao(ShopDao dao) {
        this.dao = dao;
    }
}
