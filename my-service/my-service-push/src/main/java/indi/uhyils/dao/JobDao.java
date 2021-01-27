package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.JobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface JobDao extends DefaultDao<JobEntity> {


    /**
     * 暂停一个任务
     *
     * @param id
     * @return
     */
    Integer pause(Long id);

    /**
     * 开启
     *
     * @param id id
     * @return 个数
     */
    Integer start(Long id);
}
