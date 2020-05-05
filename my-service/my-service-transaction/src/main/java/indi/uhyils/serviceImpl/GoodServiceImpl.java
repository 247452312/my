package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.GoodDao;
import indi.uhyils.model.GoodEntity;
import indi.uhyils.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 13时11分
 */
@Service
public class GoodServiceImpl extends DefaultServiceImpl<GoodEntity> implements GoodService {

    @Autowired
    private GoodDao dao;

    public GoodDao getDao() {
        return dao;
    }

    public void setDao(GoodDao dao) {
        this.dao = dao;
    }
}
