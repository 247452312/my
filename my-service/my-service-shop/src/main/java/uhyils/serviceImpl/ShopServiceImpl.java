package uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.model.ShopEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uhyils.dao.ShopDao;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时51分
 */
@Service
public class ShopServiceImpl implements ShopService {


    private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private ShopDao shopDao;

    @Override
    public ServiceResult<Page<ShopEntity>> getByArgs(ArgsRequest argsRequest) {
        return null;
    }

    @Override
    public ServiceResult<ShopEntity> getById(IdRequest idRequest) {
        return null;
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<ShopEntity> insert) {
        return null;
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<ShopEntity> update) {
        return null;
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        return null;
    }

    public ShopDao getShopDao() {
        return shopDao;
    }

    public void setShopDao(ShopDao shopDao) {
        this.shopDao = shopDao;
    }
}
