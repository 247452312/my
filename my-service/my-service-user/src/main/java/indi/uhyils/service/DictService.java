package indi.uhyils.service;


import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import indi.uhyils.pojo.entity.type.Code;
import indi.uhyils.pojo.entity.type.Identifier;
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
     * @param dto 字典项
     *
     * @return
     */
    Boolean insertItem(DictItemDTO dto);


    /**
     * 获取字典的所有字典项
     *
     * @param dictId 字典id
     *
     * @return 字典项
     */
    List<DictItemDTO> getItemByDictId(Identifier dictId);

    /**
     * 修改字典项
     *
     * @param dto   字典项
     * @param args 查询项
     *
     * @return 修改是否成功
     */
    Boolean updateItem(DictItemDTO dto, List<Arg> args);

    /**
     * 删除字典项
     *
     * @param dictItemId 字典项id
     *
     * @return 是否删除成功
     */
    Boolean deleteItem(Identifier dictItemId);


    /**
     * 清理某一个字典 即 删除所有字典项
     *
     * @param dictId 字典id
     *
     * @return 是否成功
     */
    Boolean cleanDictItem(Identifier dictId);


    /**
     * 获取字典项
     *
     * @param dictItemId 字典项id
     *
     * @return 字典项
     */
    DictItemDTO getItemById(Identifier dictItemId);

    /**
     * 根据某几列获取item数据
     *
     * @param dictId 主表id
     * @param args   查询条件
     * @param order  排序
     * @param limit  分页
     *
     * @return 分页数据(也可以设置不分页)
     */
    Page<DictItemDTO> getByItemArgs(Identifier dictId, List<Arg> args, Order order, Limit limit);


    /**
     * 获取版本信息response
     *
     * @return 版本信息
     */
    VersionInfoDTO getVersionInfoResponse();


    /**
     * 获取下一步计划
     *
     * @return 下一步计划
     */
    LastPlanDTO getLastPlanResponse();


    /**
     * 获取全部的按钮菜单
     *
     * @return 图标class
     */
    List<String> getAllMenuIcon();


    /**
     * 获取code对应的字典对应的所有项
     *
     * @param code 字典code
     *
     * @return code对应的字典对应的所有项
     */
    List<DictItemDTO> getByCode(Code code);


    /**
     * 获取开始界面快捷入口信息
     *
     * @return 开始界面快捷入口信息
     */
    QuickStartDTO getQuickStartResponse();
}
