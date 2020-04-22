package indi.uhyils.service;

import indi.uhyils.model.User;

import java.util.List;

/**
 * 学生接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时29分
 */
public interface UserService {

    /**
     * 根据学号获取学生数据
     *
     * @return
     */
    User getById(Integer id);

    /**
     * 根据班级号获取所有此班级的学生
     *
     * @param classId
     * @return
     */
    List<User> getByClassId(Integer classId);

    /**
     * 新增或修改学生
     *
     * @return
     */
    Boolean addOrUpdate(User user);

    /**
     * 删除学生
     *
     * @return
     */
    Boolean delete(Integer id);


}
