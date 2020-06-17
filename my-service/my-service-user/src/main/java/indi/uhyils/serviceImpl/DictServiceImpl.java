package indi.uhyils.serviceImpl;

import org.apache.dubbo.config.annotation.Service;
import indi.uhyils.dao.DictDao;
import indi.uhyils.dao.DictItemDao;
import indi.uhyils.pojo.model.DictEntity;
import indi.uhyils.pojo.model.DictItemEntity;
import indi.uhyils.pojo.request.GetByItemArgsRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.Page;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        return ServiceResult.buildSuccessResult("删除字典以及字典项成功", 1, idRequest);
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

    @Override
    public ServiceResult<Boolean> insertItem(ObjRequest<DictItemEntity> request) {
        DictItemEntity data = request.getData();
        data.preInsert(request);
        dictItemDao.insert(data);
        return ServiceResult.buildSuccessResult("插入字典项成功", true, request);
    }

    @Override
    public ServiceResult<ArrayList<DictItemEntity>> getItemByDictId(IdRequest request) {
        ArrayList<DictItemEntity> items = dictItemDao.getByDictId(request.getId());
        return ServiceResult.buildSuccessResult("查询成功", items, request);
    }

    @Override
    public ServiceResult<Boolean> updateItem(ObjRequest<DictItemEntity> request) {
        DictItemEntity data = request.getData();
        data.preUpdate(request);
        dictItemDao.update(data);
        return ServiceResult.buildSuccessResult("修改成功", true, request);
    }

    @Override
    public ServiceResult<Boolean> deleteItem(IdRequest request) {
        String itemId = request.getId();
        DictItemEntity dictItemEntity = dictItemDao.getById(itemId);
        dictItemEntity.preUpdate(request);
        dictItemEntity.setDeleteFlag(true);
        dictItemDao.update(dictItemEntity);
        return ServiceResult.buildSuccessResult("删除成功", true, request);
    }

    @Override
    public ServiceResult<Boolean> cleanDictItem(IdRequest request) {
        ArrayList<DictItemEntity> items = dictItemDao.getByDictId(request.getId());
        items.forEach(item -> {
            item.setDeleteFlag(true);
            item.preUpdate(request);
            dictItemDao.update(item);
        });
        return ServiceResult.buildSuccessResult("清理成功", true, request);
    }

    @Override
    public ServiceResult<DictItemEntity> getItemById(IdRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", dictItemDao.getById(request.getId()), request);
    }

    @Override
    public ServiceResult<Page<DictItemEntity>> getByItemArgs(GetByItemArgsRequest request) {
        List<Arg> args = request.getArgs();
        Boolean paging = request.getPaging();
        Arg arg = new Arg();
        arg.setName("dict_id");
        arg.setSymbol("=");
        arg.setData(request.getDictId());
        args.add(arg);
        if (paging) {
            ArrayList<DictItemEntity> byArgs = dictItemDao.getByArgs(args, request.getPage(), request.getSize());
            int count = dictItemDao.countByArgs(request.getArgs());
            Page<DictItemEntity> build = Page.build(request, byArgs, count, (count / request.getSize()) + 1);
            return ServiceResult.buildSuccessResult("查询成功", build, request);
        } else {
            ArrayList<DictItemEntity> byArgs = dictItemDao.getByArgsNoPage(args);
            int count = dictItemDao.countByArgs(request.getArgs());
            Page<DictItemEntity> build = Page.build(request, byArgs, count, null);
            return ServiceResult.buildSuccessResult("查询成功", build, request);
        }
    }
}
