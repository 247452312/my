package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.DictDao;
import indi.uhyils.dao.DictItemDao;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.DictDO;
import indi.uhyils.pojo.DO.DictItemDO;
import indi.uhyils.pojo.DTO.request.GetByCodeRequest;
import indi.uhyils.pojo.DTO.request.GetByItemArgsRequest;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.request.base.ObjRequest;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.pojo.DTO.response.LastPlanResponse;
import indi.uhyils.pojo.DTO.response.VersionInfoResponse;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.protocol.rpc.provider.DictProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
@ReadWriteMark(tables = {"sys_dict"}, cacheType = CacheTypeEnum.ALL_TYPE)
public class DictProviderImpl extends BaseDefaultProvider<DictDO> implements DictProvider {

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
        DictDO dictEntity = dao.getById(id);
        if (dictEntity == null) {
            return ServiceResult.buildFailedResult("查无此字典", null);
        }
        dictEntity.setDeleteFlag(true);
        dictEntity.preUpdate(idRequest);
        dao.update(dictEntity);
        dictItemDao.deleteByDictId(id);
        return ServiceResult.buildSuccessResult("删除字典以及字典项成功", 1);
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
    public ServiceResult<Boolean> insertItem(ObjRequest<DictItemDO> request) throws Exception {
        DictItemDO data = request.getData();
        data.preInsert(request);
        dictItemDao.insert(data);
        return ServiceResult.buildSuccessResult("插入字典项成功", true);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<ArrayList<DictItemDO>> getItemByDictId(IdRequest request) {
        ArrayList<DictItemDO> items = dictItemDao.getByDictId(request.getId());
        return ServiceResult.buildSuccessResult("查询成功", items);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> updateItem(ObjRequest<DictItemDO> request) {
        DictItemDO data = request.getData();
        data.preUpdate(request);
        dictItemDao.update(data);
        return ServiceResult.buildSuccessResult("修改成功", true);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> deleteItem(IdRequest request) {
        Long itemId = request.getId();
        DictItemDO dictItemEntity = dictItemDao.getById(itemId);
        dictItemEntity.preUpdate(request);
        dictItemEntity.setDeleteFlag(true);
        dictItemDao.update(dictItemEntity);
        return ServiceResult.buildSuccessResult("删除成功", true);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> cleanDictItem(IdRequest request) {
        ArrayList<DictItemDO> items = dictItemDao.getByDictId(request.getId());
        items.forEach(item -> {
            item.setDeleteFlag(true);
            item.preUpdate(request);
            dictItemDao.update(item);
        });
        return ServiceResult.buildSuccessResult("清理成功", true);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<DictItemDO> getItemById(IdRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", dictItemDao.getById(request.getId()));
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<Page<DictItemDO>> getByItemArgs(GetByItemArgsRequest request) {
        List<Arg> args = request.getArgs();
        Boolean paging = request.getPaging();
        Arg arg = new Arg();
        arg.setName("dict_id");
        arg.setSymbol("=");
        arg.setData(request.getDictId());
        args.add(arg);
        if (Boolean.TRUE.equals(paging)) {
            ArrayList<DictItemDO> byArgs = dictItemDao.getByArgs(args, request.getPage(), request.getSize());
            int count = dictItemDao.countByArgs(request.getArgs());
            Page<DictItemDO> build = Page.build(request, byArgs, count, (count / request.getSize()) + 1);
            return ServiceResult.buildSuccessResult("查询成功", build);
        } else {
            ArrayList<DictItemDO> byArgs = dictItemDao.getByArgsNoPage(args);
            int count = dictItemDao.countByArgs(request.getArgs());
            Page<DictItemDO> build = Page.build(request, byArgs, count, null);
            return ServiceResult.buildSuccessResult("查询成功", build);
        }
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<VersionInfoResponse> getVersionInfoResponse(DefaultRequest request) {
        Long dictId = dao.getIdByCode(VERSION_CODE);
        ArrayList<DictItemDO> infos = dictItemDao.getByDictId(dictId);
        VersionInfoResponse build = VersionInfoResponse.build(infos);
        return ServiceResult.buildSuccessResult("查询成功", build);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<LastPlanResponse> getLastPlanResponse(DefaultRequest request) {
        Long dictId = dao.getIdByCode(LAST_PLAN_CODE);
        ArrayList<DictItemDO> infos = dictItemDao.getByDictId(dictId);
        LastPlanResponse build = LastPlanResponse.build(infos);
        return ServiceResult.buildSuccessResult("查询成功", build);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<ArrayList<Object>> getAllMenuIcon(DefaultRequest request) {
        Long dictId = dao.getIdByCode(MENU_ICON_CLASS_CODE);
        ArrayList<DictItemDO> infos = dictItemDao.getByDictId(dictId);
        ArrayList<Object> collect = (ArrayList<Object>) infos.stream().map(DictItemDO::getValue).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("查询成功", collect);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"}, cacheType = CacheTypeEnum.ALL_TYPE)
    public ServiceResult<ArrayList<DictItemDO>> getByCode(GetByCodeRequest request) {
        ArrayList<DictItemDO> list = dictItemDao.getByCode(request.getCode());
        return ServiceResult.buildSuccessResult("查询成功", list);
    }

}
