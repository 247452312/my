package indi.uhyils.dao;

import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.DeptPowerMiddle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface DeptDao extends DefaultDao<DeptEntity> {

    Boolean insertDeptPower(DeptPowerMiddle middle);

    boolean deleteDeptPower(List<String> ids);

}
