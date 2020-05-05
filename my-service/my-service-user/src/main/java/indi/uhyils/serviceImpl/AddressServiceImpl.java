package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.AddressDao;
import indi.uhyils.model.AddressEntity;
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
public class AddressServiceImpl extends DefaultServiceImpl<AddressEntity> implements AddressService {

    @Autowired
    private AddressDao dao;

    public AddressDao getDao() {
        return dao;
    }

    public void setDao(AddressDao dao) {
        this.dao = dao;
    }
}
