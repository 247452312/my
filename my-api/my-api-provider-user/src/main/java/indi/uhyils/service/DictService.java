package indi.uhyils.service;

import indi.uhyils.pojo.model.DictEntity;
import indi.uhyils.pojo.model.DictItemEntity;
import indi.uhyils.pojo.request.GetByCodeRequest;
import indi.uhyils.pojo.request.GetByItemArgsRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.response.LastPlanResponse;
import indi.uhyils.pojo.response.VersionInfoResponse;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

import java.util.ArrayList;

/**
 * 字典接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface DictService extends DefaultEntityService<DictEntity> {

    /**
     * 新建新的字典项
     *
     * @param request 字典项
     * @return
     */
    ServiceResult<Boolean> insertItem(ObjRequest<DictItemEntity> request) throws Exception;


    /**
     * 获取字典的所有字典项
     *
     * @param request 字典id
     * @return 字典项
     */
    ServiceResult<ArrayList<DictItemEntity>> getItemByDictId(IdRequest request);

    /**
     * 修改字典项
     *
     * @param request 字典项
     * @return 修改是否成功
     */
    ServiceResult<Boolean> updateItem(ObjRequest<DictItemEntity> request);

    /**
     * 删除字典项
     *
     * @param request 字典项id
     * @return 是否删除成功
     */
    ServiceResult<Boolean> deleteItem(IdRequest request);


    /**
     * 清理某一个字典 即 删除所有字典项
     *
     * @param request 字典id
     * @return 是否成功
     */
    ServiceResult<Boolean> cleanDictItem(IdRequest request);


    /**
     * 获取字典项
     *
     * @param request 字典项id
     * @return 字典项
     */
    ServiceResult<DictItemEntity> getItemById(IdRequest request);

    /**
     * 根据某几列获取item数据
     *
     * @param request 根据列名获取信息
     * @return 分页数据(也可以设置不分页)
     */
    ServiceResult<Page<DictItemEntity>> getByItemArgs(GetByItemArgsRequest request);


    /**
     * 获取版本信息response
     *
     * @param request 默认请求
     * @return 版本信息
     */
    ServiceResult<VersionInfoResponse> getVersionInfoResponse(DefaultRequest request);


    /**
     * 获取下一步计划
     *
     * @param request 默认
     * @return 下一步计划
     */
    ServiceResult<LastPlanResponse> getLastPlanResponse(DefaultRequest request);


    /**
     * 获取全部的按钮菜单
     *
     * @param request 默认请求
     * @return 图标class
     */
    ServiceResult<ArrayList<Object>> getAllMenuIcon(DefaultRequest request);


    /**
     * 获取code对应的字典对应的所有项
     *
     * @param request 字典code
     * @return code对应的字典对应的所有项
     */
    ServiceResult<ArrayList<DictItemEntity>> getByCode(GetByCodeRequest request);
}
