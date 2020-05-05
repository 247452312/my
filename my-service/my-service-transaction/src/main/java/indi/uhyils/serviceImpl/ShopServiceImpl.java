package indi.uhyils.serviceImpl;

import indi.uhyils.model.ShopEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.ShopService;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时08分
 */
public class ShopServiceImpl implements ShopService {
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
