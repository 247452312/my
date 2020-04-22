package indi.uhyils.service;

import indi.uhyils.model.Class;

/**
 * 班级接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时33分
 */
public interface ClassService {

    /**
     * 根据id获取
     *
     * @param id 用户id
     * @return
     */
    Class getById(Integer id);

    /**
     * 添加或修改
     *
     * @param cls 班级
     * @return
     */
    Boolean addOrUpdate(Class cls);

    /**
     * 删除
     *
     * @param id 班级id
     * @return
     */
    Boolean delete(Integer id);

}
