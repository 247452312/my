package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.GoodDao;
import indi.uhyils.model.GoodEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 13时11分
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodDao goodDao;

    @Override
    public ServiceResult<Page<GoodEntity>> getByArgs(ArgsRequest argsRequest) {
        return null;
    }

    @Override
    public ServiceResult<GoodEntity> getById(IdRequest idRequest) {
        return null;
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<GoodEntity> insert) {
        return null;
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<GoodEntity> update) {
        return null;
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        return null;
    }

    public GoodDao getGoodDao() {
        return goodDao;
    }

    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }
}
