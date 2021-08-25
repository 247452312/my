package indi.uhyils.repository;

import indi.uhyils.pojo.entity.Power;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseRepository;
import java.util.List;

/**
 * 权限仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时46分
 */
public interface PowerRepository extends BaseRepository<Power> {

    /**
     * 根据部门id获取权限集
     *
     * @param deptId
     *
     * @return
     */
    List<Power> findByDeptId(Identifier deptId);
}
