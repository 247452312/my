package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.DictDao;
import indi.uhyils.dao.DictItemDao;
import indi.uhyils.pojo.model.DictEntity;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@Service(group = "${spring.profiles.active}")
public class DictServiceImpl extends BaseDefaultServiceImpl<DictEntity> implements DictService {

    @Autowired
    private DictDao dao;

    @Autowired
    private DictItemDao dictItemDao;

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        String id = idRequest.getId();
        DictEntity dictEntity = dao.getById(id);
        if (dictEntity == null) {
            return ServiceResult.buildFailedResult("查无此字典", null, idRequest);
        }
        dictEntity.setDeleteFlag(true);
        dictEntity.preUpdate(idRequest);
        dao.update(dictEntity);
        dictItemDao.deleteByDictId(id);
        return ServiceResult.buildSuccessResult("删除字典以及字典项成功", null, idRequest);
    }

    public DictDao getDao() {
        return dao;
    }

    public void setDao(DictDao dao) {
        this.dao = dao;
    }

    public DictItemDao getDictItemDao() {
        return dictItemDao;
    }

    public void setDictItemDao(DictItemDao dictItemDao) {
        this.dictItemDao = dictItemDao;
    }
}
