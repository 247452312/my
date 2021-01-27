package indi.uhyils.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.DictDao;
import indi.uhyils.dao.DictItemDao;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.DictEntity;
import indi.uhyils.pojo.model.DictItemEntity;
import indi.uhyils.pojo.request.GetByCodeRequest;
import indi.uhyils.pojo.request.GetByItemArgsRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.LastPlanResponse;
import indi.uhyils.pojo.response.VersionInfoResponse;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.DictService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
@ReadWriteMark(tables = {"sys_dict"}, cacheType = CacheTypeEnum.ALL_TYPE)
public class DictServiceImpl extends BaseDefaultServiceImpl<DictEntity> implements DictService {

    /**
     * 首页版本信息展示 字典code
     */
    private static final String VERSION_CODE = "myVersionCode";


    /**
     * 首页下一步计划展示 字典code
     */
    private static final String LAST_PLAN_CODE = "lastPlan";

    /**
     * 图标的icon-class
     */
    private static final String MENU_ICON_CLASS_CODE = "icon-class";


    @Resource
    private DictDao dao;

    @Resource
    private DictItemDao dictItemDao;

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict", "sys_dict_item"})
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        Long id = idRequest.getId();
        DictEntity dictEntity = dao.selectById(id);
        if (dictEntity == null) {
            return ServiceResult.buildFailedResult("查无此字典", null, idRequest);
        }
        dictEntity.setDeleteFlag(true);
        dictEntity.preUpdate(idRequest);
        dao.updateById(dictEntity);
        dictItemDao.deleteByDictId(id);
        return ServiceResult.buildSuccessResult("删除字典以及字典项成功", 1, idRequest);
    }

    @Override
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
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> insertItem(ObjRequest<DictItemEntity> request) throws Exception {
        DictItemEntity data = request.getData();
        data.preInsert(request);
        dictItemDao.insert(data);
        return ServiceResult.buildSuccessResult("插入字典项成功", true, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<ArrayList<DictItemEntity>> getItemByDictId(IdRequest request) {
        ArrayList<DictItemEntity> items = dictItemDao.getByDictId(request.getId());
        return ServiceResult.buildSuccessResult("查询成功", items, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> updateItem(ObjRequest<DictItemEntity> request) {
        DictItemEntity data = request.getData();
        data.preUpdate(request);
        dictItemDao.updateById(data);
        return ServiceResult.buildSuccessResult("修改成功", true, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> deleteItem(IdRequest request) {
        Long itemId = request.getId();
        DictItemEntity dictItemEntity = dictItemDao.selectById(itemId);
        dictItemEntity.preUpdate(request);
        dictItemEntity.setDeleteFlag(true);
        dictItemDao.updateById(dictItemEntity);
        return ServiceResult.buildSuccessResult("删除成功", true, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> cleanDictItem(IdRequest request) {
        ArrayList<DictItemEntity> items = dictItemDao.getByDictId(request.getId());
        items.forEach(item -> {
            item.setDeleteFlag(true);
            item.preUpdate(request);
            dictItemDao.updateById(item);
        });
        return ServiceResult.buildSuccessResult("清理成功", true, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<DictItemEntity> getItemById(IdRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", dictItemDao.selectById(request.getId()), request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<Page<DictItemEntity>> getByItemArgs(GetByItemArgsRequest request) {
        List<Arg> args = request.getArgs();
        Boolean paging = request.getPaging();
        Page<DictItemEntity> build;
        Arg arg = new Arg();
        arg.setName("dict_id");
        arg.setSymbol("=");
        arg.setData(request.getDictId());
        args.add(arg);
        QueryWrapper<DictItemEntity> queryWrapper = getQueryWrapper(request.getArgs(),DictItemEntity.class);
        if (paging) {
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<DictItemEntity> tPage = dictItemDao.selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(request.getPage(), request.getSize()), queryWrapper);
            build = Page.build(request, tPage.getRecords(), tPage.getTotal(), tPage.getTotal() / tPage.getSize());
        } else {
            List<DictItemEntity> ts = dictItemDao.selectList(queryWrapper);
            Integer integer = dictItemDao.selectCount(queryWrapper);
            build = Page.build(request, ts, integer.longValue(), null);
        }
        return ServiceResult.buildSuccessResult("查询成功", build, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<VersionInfoResponse> getVersionInfoResponse(DefaultRequest request) {
        // TODO 版本信息应该加入在缓存里
        Long dictId = dao.getIdByCode(VERSION_CODE);
        ArrayList<DictItemEntity> infos = dictItemDao.getByDictId(dictId);
        VersionInfoResponse build = VersionInfoResponse.build(infos);
        return ServiceResult.buildSuccessResult("查询成功", build, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<LastPlanResponse> getLastPlanResponse(DefaultRequest request) {
        // TODO 下一步计划应该在缓存里
        Long dictId = dao.getIdByCode(LAST_PLAN_CODE);
        ArrayList<DictItemEntity> infos = dictItemDao.getByDictId(dictId);
        LastPlanResponse build = LastPlanResponse.build(infos);
        return ServiceResult.buildSuccessResult("查询成功", build, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<ArrayList<Object>> getAllMenuIcon(DefaultRequest request) {
        Long dictId = dao.getIdByCode(MENU_ICON_CLASS_CODE);
        ArrayList<DictItemEntity> infos = dictItemDao.getByDictId(dictId);
        ArrayList<Object> collect = (ArrayList<Object>) infos.stream().map(DictItemEntity::getValue).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("查询成功", collect, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"}, cacheType = CacheTypeEnum.ALL_TYPE)
    public ServiceResult<ArrayList<DictItemEntity>> getByCode(GetByCodeRequest request) {
        ArrayList<DictItemEntity> list = dictItemDao.getByCode(request.getCode());
        return ServiceResult.buildSuccessResult("查询成功", list, request);
    }

}
