package indi.uhyils.service;

import indi.uhyils.pojo.request.ArgsRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.response.Page;
import indi.uhyils.pojo.response.ServiceResult;

import java.io.Serializable;

/**
 * 如果是一个EntityService 就应该继承这个类,包含增删改以及
 * 根据id查询
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时29分
 */
public interface DefaultEntityService<T extends Serializable> {


    /**
     * 根据某几列获取数据
     *
     * @return
     */
    ServiceResult<Page<T>> getByArgs(ArgsRequest argsRequest);

    /**
     * 根据id查询
     *
     * @param idRequest
     * @return
     */
    ServiceResult<T> getById(IdRequest idRequest);


    /**
     * 插入
     *
     * @param insert
     * @return
     */
    ServiceResult<Integer> insert(ObjRequest<T> insert);

    /**
     * 修改
     *
     * @param update
     * @return
     */
    ServiceResult<Integer> update(ObjRequest<T> update);


    /**
     * 删除
     *
     * @param idRequest
     * @return
     */
    ServiceResult<Integer> delete(IdRequest idRequest);

}
