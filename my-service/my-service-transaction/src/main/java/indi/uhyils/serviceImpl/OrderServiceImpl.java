package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.model.OrderEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.OrderService;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 13时14分
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public ServiceResult<Page<OrderEntity>> getByArgs(ArgsRequest argsRequest) {
        return null;
    }

    @Override
    public ServiceResult<OrderEntity> getById(IdRequest idRequest) {
        return null;
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<OrderEntity> insert) {
        return null;
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<OrderEntity> update) {
        return null;
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        return null;
    }
}
