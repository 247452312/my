package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.DO.ApiGroupDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface ApiDao extends DefaultDao<ApiDO> {

    /**
     * 获取某个api群的所有api -> 排序 从小到大
     *
     * @param apiGroup api群名称
     *
     * @return apis
     */
    List<ApiDO> getGroupByGroupId(Long apiGroup);

    /**
     * 获取全部api
     *
     * @return 全部api
     */
    List<ApiDO> getAll();

    /**
     * 删除所有
     *
     * @param byId
     *
     * @return
     */
    int deleteAllByGroup(ApiGroupDO byId);
}
