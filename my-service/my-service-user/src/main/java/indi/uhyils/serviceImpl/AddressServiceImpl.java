package indi.uhyils.serviceImpl;

import indi.uhyils.model.AddressEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.AddressService;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时49分
 */
public class AddressServiceImpl implements AddressService {
    @Override
    public ServiceResult<Page<AddressEntity>> getByArgs(ArgsRequest argsRequest) {
        return null;
    }

    @Override
    public ServiceResult<AddressEntity> getById(IdRequest idRequest) {
        return null;
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<AddressEntity> insert) {
        return null;
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<AddressEntity> update) {
        return null;
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        return null;
    }
}
