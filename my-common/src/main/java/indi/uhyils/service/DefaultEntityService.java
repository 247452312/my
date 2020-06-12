package indi.uhyils.service;

import indi.uhyils.pojo.model.base.DataEntity;
import indi.uhyils.pojo.request.ArgsRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.response.Page;
import indi.uhyils.pojo.response.ServiceResult;

/**
 * 如果是一个EntityService 就应该继承这个类,包含增删改以及
 * 根据id查询
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时29分
 */
public interface DefaultEntityService<T extends DataEntity> {


    /**
     * 根据某几列获取数据
     *
     * @param argsRequest 根据列名获取信息
     * @return 分页数据(也可以设置不分页)
     */
    ServiceResult<Page<T>> getByArgs(ArgsRequest argsRequest);

    /**
     * 根据id查询
     *
     * @param idRequest id
     * @return 单条
     */
    ServiceResult<T> getById(IdRequest idRequest);


    /**
     * 插入
     *
     * @param insert 插入信息,要求是entity
     * @return 插入条数
     */
    ServiceResult<Integer> insert(ObjRequest<T> insert);

    /**
     * 修改
     *
     * @param update 修改信息,要求是entity,并且要求有id
     * @return 修改条数
     */
    ServiceResult<Integer> update(ObjRequest<T> update);


    /**
     * 删除
     *
     * @param idRequest id
     * @return 删除条数
     */
    ServiceResult<Integer> delete(IdRequest idRequest);


    /**
     * 数量
     *
     * @param argsRequest 默认不分页,不可配置是否分页
     * @return 根据条件查询出来的数量
     */
    ServiceResult<Integer> countByArgs(ArgsRequest argsRequest);

}
