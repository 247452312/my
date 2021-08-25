package indi.uhyils.repository;

import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseRepository;
import java.util.List;

/**
 * 角色仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时46分
 */
public interface DeptRepository extends BaseRepository<Dept> {

    /**
     * 根据角色id获取全部dept
     *
     * @param roleId
     *
     * @return
     */
    List<Dept> findByRoleId(Identifier roleId);
}
