package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.pojo.model.ApiGroupEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface ApiDao extends DefaultDao<ApiEntity> {

    /**
     * 获取某个api群的所有api -> 排序 从小到大
     *
     * @param apiGroup api群名称
     * @return apis
     */
    List<ApiEntity> getGroupByGroupId(String apiGroup);

    /**
     * 获取全部api
     *
     * @return 全部api
     */
    List<ApiEntity> getAll();

    /**
     * 删除所有
     *
     * @param byId
     * @return
     */
    int deleteAllByGroup(ApiGroupEntity byId);
}
