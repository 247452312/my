package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.AddressDao;
import indi.uhyils.dao.UserDao;
import indi.uhyils.model.AddressEntity;
import indi.uhyils.model.UserEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.request.model.Arg;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时49分
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public ServiceResult<Page<AddressEntity>> getByArgs(ArgsRequest argsRequest) {
        List<Arg> args = argsRequest.getArgs();
        Boolean paging = argsRequest.getPaging();
        if (paging == true) {
            ArrayList<AddressEntity> byArgs = addressDao.getByArgs(args, argsRequest.getPage(), argsRequest.getSize());
            int count = addressDao.count();
            Page<AddressEntity> build = Page.build(argsRequest, byArgs, count, (count / argsRequest.getSize()) + 1);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        } else {
            ArrayList<AddressEntity> byArgs = addressDao.getByArgsNoPage(args);
            int count = addressDao.count();
            Page<AddressEntity> build = Page.build(argsRequest, byArgs, count, null);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        }
    }

    @Override
    public ServiceResult<AddressEntity> getById(IdRequest idRequest) {
        List<AddressEntity> byId = addressDao.getById(idRequest.getId());
        if (byId != null && byId.size() == 1) {
            return ServiceResult.buildSuccessResult("查询成功", byId.get(0), idRequest);
        }
        return ServiceResult.buildFailedResult("查无此人", null, idRequest);
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<AddressEntity> insert) {
        AddressEntity data = insert.getData();
        data.preInsert(insert);
        int count = addressDao.insert(data);
        if (count == 1) {
            return ServiceResult.buildSuccessResult("创建成功", count, insert);
        }
        return ServiceResult.buildFailedResult("创建失败", 0, insert);
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<AddressEntity> update) {
        AddressEntity data = update.getData();
        data.preUpdate(update);
        int count = addressDao.update(data);
        if (count != 0) {
            return ServiceResult.buildSuccessResult("修改成功", count, update);
        } else {
            return ServiceResult.buildFailedResult("修改失败", 0, update);
        }
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        int delete = addressDao.delete(idRequest.getId());
        if (delete != 0) {
            return ServiceResult.buildSuccessResult("删除成功", delete, idRequest);
        } else {
            return ServiceResult.buildFailedResult("删除失败", delete, idRequest);
        }
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
}
