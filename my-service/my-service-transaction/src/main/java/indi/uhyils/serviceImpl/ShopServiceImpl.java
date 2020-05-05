package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.ShopDao;
import indi.uhyils.model.ShopEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时08分
 */
@Service
public class ShopServiceImpl implements ShopService {

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
}
