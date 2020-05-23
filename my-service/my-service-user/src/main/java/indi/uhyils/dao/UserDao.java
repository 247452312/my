package indi.uhyils.dao;

import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.model.UserRightEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface UserDao extends DefaultDao<UserEntity> {

    ArrayList<UserRightEntity> getUserRightsByUserId(String userId);

    UserRightEntity getUserRightsByRightId(String userRightId);

    Integer checkRepeat(@Param("value") String value, @Param("type") String type);
}
