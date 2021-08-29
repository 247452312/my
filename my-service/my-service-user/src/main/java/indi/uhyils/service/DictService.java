package indi.uhyils.service;


import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.request.GetByCodeRequest;
import indi.uhyils.pojo.DTO.request.GetByItemArgsQuery;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import java.util.List;

/**
 * 数据字典(Dict)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分37秒
 */
public interface DictService extends BaseDoService<DictDTO> {

    /**
     * 新建新的字典项
     *
     * @param request 字典项
     *
     * @return
     */
    Boolean insertItem(AddCommand<DictItemDTO> request);


    /**
     * 获取字典的所有字典项
     *
     * @param request 字典id
     *
     * @return 字典项
     */
    List<DictItemDTO> getItemByDictId(IdQuery request);

    /**
     * 修改字典项
     *
     * @param request 字典项
     *
     * @return 修改是否成功
     */
    Boolean updateItem(ChangeCommand<DictItemDTO> request);

    /**
     * 删除字典项
     *
     * @param request 字典项id
     *
     * @return 是否删除成功
     */
    Boolean deleteItem(IdCommand request);


    /**
     * 清理某一个字典 即 删除所有字典项
     *
     * @param request 字典id
     *
     * @return 是否成功
     */
    Boolean cleanDictItem(IdCommand request);


    /**
     * 获取字典项
     *
     * @param request 字典项id
     *
     * @return 字典项
     */
    DictItemDTO getItemById(IdQuery request);

    /**
     * 根据某几列获取item数据
     *
     * @param request 根据列名获取信息
     *
     * @return 分页数据(也可以设置不分页)
     */
    Page<DictItemDTO> getByItemArgs(GetByItemArgsQuery request);


    /**
     * 获取版本信息response
     *
     * @param request 默认请求
     *
     * @return 版本信息
     */
    VersionInfoDTO getVersionInfoResponse(DefaultCQE request);


    /**
     * 获取下一步计划
     *
     * @param request 默认
     *
     * @return 下一步计划
     */
    LastPlanDTO getLastPlanResponse(DefaultCQE request);


    /**
     * 获取全部的按钮菜单
     *
     * @param request 默认请求
     *
     * @return 图标class
     */
    List<String> getAllMenuIcon(DefaultCQE request);


    /**
     * 获取code对应的字典对应的所有项
     *
     * @param request 字典code
     *
     * @return code对应的字典对应的所有项
     */
    List<DictItemDTO> getByCode(GetByCodeRequest request);


    /**
     * 获取开始界面快捷入口信息
     *
     * @param request 默认请求
     *
     * @return 开始界面快捷入口信息
     */
    QuickStartDTO getQuickStartResponse(DefaultCQE request);
}
